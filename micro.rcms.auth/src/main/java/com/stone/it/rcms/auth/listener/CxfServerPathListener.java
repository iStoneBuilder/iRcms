package com.stone.it.rcms.auth.listener;

import com.alibaba.fastjson2.JSON;
import com.stone.it.rcms.auth.service.IAuthSettingService;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.auth.vo.AuthApisVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    // 接口信息集合
    private static final List<AuthApisVO> currentApiList = new ArrayList<>();
    // 权限编码集合 用于判断是否重复添加
    private static final Set<String> authCodeSet = new HashSet<>();
    // 接口路径集合
    private static final Set<String> apiPathSet = new HashSet<>();

    private static String getAnnotationValue(String name, Class<?> annotationType, Annotation iAnnotation) {
        // 获取注解定义的所有方法
        Method[] methods = annotationType.getDeclaredMethods();
        for (Method method : methods) {
            try {
                // 获取每个方法的返回值
                Object value = method.invoke(iAnnotation);
                // 打印注解属性的名称和值
                if (name.equals(method.getName())) {
                    return ((String[])value)[0];
                }
            } catch (Exception e) {
                LOGGER.error("Get permission annotation value error.", e);
            }
        }
        return null;
    }

    private static void buildPermission(OperationResourceInfo operationResource, AuthApisVO AuthApisVO) {
        // 获取权限注解
        Annotation[] annotationList = operationResource.getOutAnnotations();
        for (int i = 0; i < annotationList.length; i++) {
            Annotation iAnnotation = annotationList[i];
            Class<?> annotationType = iAnnotation.annotationType();
            String annotationName = annotationType.getName();
            // 判断是否需要权限验证
            if ("org.apache.shiro.authz.annotation.RequiresPermissions".equals(annotationName)) {
                String permission = getAnnotationValue("value", annotationType, iAnnotation);
                if (permission != null) {
                    AuthApisVO.setAuthCode(permission);
                }
            }
            if ("com.stone.it.rcms.core.annotate.RcmsMethod".equals(annotationName)) {
                String apiName = getAnnotationValue("name", annotationType, iAnnotation);
                if (apiName != null) {
                    AuthApisVO.setApiName(apiName);
                }
                String apiType = getAnnotationValue("type", annotationType, iAnnotation);
                if (apiType != null) {
                    AuthApisVO.setOpenApi(apiType);
                }
            }
        }
        // 判断权限编码是否重复添加
        if (!StringUtils.isEmpty(AuthApisVO.getAuthCode()) && !authCodeSet.add(AuthApisVO.getAuthCode())) {
            throw new RcmsApplicationException(500, "Duplicate permission code : " + AuthApisVO.getAuthCode());
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
                AuthApisVO authApisVO = new AuthApisVO();
                // 方法名称
                authApisVO.setApiName(operationResource.getAnnotatedMethod().getName());
                // 方法请求类型
                authApisVO.setApiMethod(operationResource.getHttpMethod());
                // 方法路径
                String methodPath = operationResource.getURITemplate().getValue();
                String apiPath = buildApiPath(contextPath + "/services", endpointPath, servicePath, methodPath);
                authApisVO.setApiPath(apiPath);
                authApisVO.setApiType(apiPath.contains("/services/rcms/") ? "system" : "business");
                // 获取是否需要权限验证
                buildPermission(operationResource, authApisVO);
                LOGGER.info("RCMS api info : {}", JSON.toJSONString(authApisVO));
                apiPathSet.add(operationResource.getHttpMethod() + "_" + apiPath);
                authApisVO.setCreateBy("system");
                authApisVO.setUpdateBy("system");
                authApisVO.setApiCode(UUIDUtil.getUuid());
                currentApiList.add(authApisVO);
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
        try {
            // 存储权限信息
            storePermission(event);
        } catch (Exception e) {
            LOGGER.error("RCMS api services store apis error.");
            e.printStackTrace();
        }
    }

    private void storePermission(ContextRefreshedEvent event) {
        LOGGER.info("RCMS api services scan end.apiPathSet:{},currentApiList:  {}", apiPathSet.size(),
            currentApiList.size());
        IAuthSettingService authSettingService = event.getApplicationContext().getBean(IAuthSettingService.class);
        // 根据路径判断数据库是否存在（接口路径唯一）
        List<AuthApisVO> dbExistApis = authSettingService.findApiPathsByPaths(apiPathSet);
        List<AuthApisVO> dbNotExistApiList = new ArrayList<>();
        for (int i = 0; i < currentApiList.size(); i++) {
            boolean isExist = false;
            for (int j = 0; j < dbExistApis.size(); j++) {
                if (currentApiList.get(i).getApiPath().equals(dbExistApis.get(j).getApiPath())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                dbNotExistApiList.add(currentApiList.get(i));
            }
        }
        // 创建新增接口
        if (dbNotExistApiList.size() > 0) {
            authSettingService.createApiPaths(dbNotExistApiList);
        }
        // 清理已删除接口
        authSettingService.deleteApiPathsNotInList(apiPathSet);
        // 清理不存在的授权关系
        authSettingService.deleteApisRelationAuth();
        // 创建超级管理员权限
        authSettingService.createSuperAdminAuth(authCodeSet, "platformAdmin");
    }

}
