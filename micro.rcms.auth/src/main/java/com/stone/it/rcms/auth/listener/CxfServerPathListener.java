package com.stone.it.rcms.auth.listener;

import com.alibaba.fastjson2.JSON;
import com.stone.it.rcms.auth.service.IPermissionService;
import com.stone.it.rcms.auth.vo.PermissionVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 应用启动注册接口
 * 
 * @author cj.stone
 * @Desc
 */

@Component
public class CxfServerPathListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CxfServerPathListener.class);

    private static final List<PermissionVO> permissionList = new ArrayList<>();

    private static final Set<String> permissinSet = new HashSet<>();

    private static final Set<String> permissinPathSet = new HashSet<>();

    @Inject
    private static IPermissionService permissionService;

    private static void buildPermission(OperationResourceInfo operationResource, PermissionVO permissionVO) {
        // 获取权限注解
        Annotation[] annotationList = operationResource.getOutAnnotations();
        for (int i = 0; i < annotationList.length; i++) {
            Annotation iAnnotation = annotationList[i];
            Class<?> annotationType = iAnnotation.annotationType();
            String annotationName = annotationType.getName();//
            if ("org.apache.shiro.authz.annotation.RequiresPermissions".equals(annotationName)) {
                // 获取注解定义的所有方法
                Method[] methods = annotationType.getDeclaredMethods();
                for (Method method : methods) {
                    try {
                        // 获取每个方法的返回值
                        Object value = method.invoke(iAnnotation);
                        // 打印注解属性的名称和值
                        if ("value".equals(method.getName())) {
                            String[] permission = (String[])value;
                            permissionVO.setPermissionCode(permission[0]);
                        }
                    } catch (Exception e) {
                        LOGGER.error("Get permission annotation value error.", e);
                    }
                }
            }
        }
        // 判断权限编码是否重复添加
        if (!StringUtils.isEmpty(permissionVO.getPermissionCode())
            && !permissinSet.add(permissionVO.getPermissionCode())) {
            throw new RcmsApplicationException(500, "Duplicate permission code : " + permissionVO.getPermissionCode());
        }
    }

    private static String buildApiPath(String contextPath, String endpointPath, String servicePath, String methodPath) {
        String apiPath = contextPath + (endpointPath.startsWith("/") ? endpointPath : "/" + endpointPath);
        apiPath = apiPath + (servicePath.startsWith("/") ? servicePath : "/" + servicePath);
        apiPath = apiPath + (methodPath.startsWith("/") ? methodPath : "/" + methodPath);
        return apiPath.replaceAll("//", "/");
    }

    private void getCxfEndpointPaths(JAXRSServerFactoryBean serverFactory, String contextPath) {
        // jaxrs:server 接口暴露配置的路径
        String endpointPath = serverFactory.getAddress();
        // 获取接口暴露下的service
        List<ClassResourceInfo> classResources = serverFactory.getServiceFactory().getClassResourceInfo();
        // 循环处理service 接口方法
        for (ClassResourceInfo classResource : classResources) {
            // service 总接口路径
            String servicePath = classResource.getURITemplate().getValue();
            // 获取接口所有方法
            Set<OperationResourceInfo> opera = classResource.getMethodDispatcher().getOperationResourceInfos();
            for (OperationResourceInfo operationResource : opera) {
                PermissionVO permissionVO = new PermissionVO();
                // 方法名称
                permissionVO.setApiName(operationResource.getAnnotatedMethod().getName());
                // 方法请求类型
                permissionVO.setRequestType(operationResource.getHttpMethod());
                // 方法路径
                String methodPath = operationResource.getURITemplate().getValue();
                String apiPath = buildApiPath(contextPath + "/services", endpointPath, servicePath, methodPath);
                permissionVO.setApiPath(apiPath);
                permissinPathSet.add(apiPath);
                permissionVO.setApiType(apiPath.contains("/services/rcms/") ? "system" : "business");
                // 获取是否需要权限验证
                buildPermission(operationResource, permissionVO);
                LOGGER.info("RCMS api info : {}", JSON.toJSONString(permissionVO));
                permissionList.add(permissionVO);
            }
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("RCMS api services scan start.");
        ApplicationContext context = event.getApplicationContext();
        // 获取服务跟路径
        String contextPath = context.getEnvironment().getProperty("server.servlet.context-path");
        // 获取所有配置的接口暴露
        String[] beanNames = context.getBeanNamesForType(JAXRSServerFactoryBean.class);
        // 循环处理 jaxrs:server
        for (String beanName : beanNames) {
            getCxfEndpointPaths(context.getBean(beanName, JAXRSServerFactoryBean.class), contextPath);
        }
        // 存储权限信息
        storePermission();
    }

    private void storePermission() {
        // 根据路径判断数据库是否存在（接口路径唯一）
        List<PermissionVO> existPermission = permissionService.findPermissionByPaths(permissinPathSet);
        List<PermissionVO> newPermissionList = new ArrayList<>();
        for (int i = 0; i < permissionList.size(); i++) {
            boolean isExist = false;
            for (int j = 0; j < existPermission.size(); j++) {
                if (permissionList.get(i).getApiPath().equals(existPermission.get(j).getApiPath())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                newPermissionList.add(permissionList.get(i));
            }
        }
        // 创建新增接口
        permissionService.createPermission(newPermissionList);
        // 清理已删除接口
        permissionService.deletePermissionNotInList(permissinPathSet);
    }

}
