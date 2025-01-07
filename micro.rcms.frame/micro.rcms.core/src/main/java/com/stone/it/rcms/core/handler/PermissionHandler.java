package com.stone.it.rcms.core.handler;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.vo.PermissionVO;
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

/**
 *
 * @author cj.stone
 * @Date 2024/11/18
 * @Desc
 */
public class PermissionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionHandler.class);
    // 权限编码集合 用于判断是否重复添加
    public static Set<String> AUTH_CODE_SET = new HashSet<>();
    // 接口信息集合
    public static List<PermissionVO> CURRENT_API_LIST = new ArrayList<>();

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

    private static void buildPermission(OperationResourceInfo operationResource, PermissionVO permissionVO) {
        // 获取权限注解
        Annotation[] annotationList = operationResource.getOutAnnotations();
        for (Annotation iAnnotation : annotationList) {
            Class<?> annotationType = iAnnotation.annotationType();
            String annotationName = annotationType.getName();
            // 判断是否需要权限验证
            if ("org.apache.shiro.authz.annotation.RequiresPermissions".equals(annotationName)) {
                String permission = getAnnotationValue("value", annotationType, iAnnotation);
                if (permission != null) {
                    permissionVO.setAuthCode(permission);
                }
            }
            if ("com.stone.it.rcms.core.annotate.RcmsMethod".equals(annotationName)) {
                String apiName = getAnnotationValue("name", annotationType, iAnnotation);
                if (apiName != null) {
                    permissionVO.setName(apiName);
                }
                String apiType = getAnnotationValue("type", annotationType, iAnnotation);
                if (apiType != null) {
                    permissionVO.setIsOpenApi(apiType);
                }
            }
        }
        // 判断权限编码是否重复添加
        if (!StringUtils.isEmpty(permissionVO.getAuthCode()) && !AUTH_CODE_SET.add(permissionVO.getAuthCode())) {
            throw new RcmsApplicationException(500, "Duplicate permission code : " + permissionVO.getAuthCode());
        }
    }

    private static String buildApiPath(String contextPath, String endpointPath, String servicePath, String methodPath) {
        String apiPath = contextPath + (endpointPath.startsWith("/") ? endpointPath : "/" + endpointPath);
        apiPath = apiPath + (servicePath.startsWith("/") ? servicePath : "/" + servicePath);
        apiPath = apiPath + (methodPath.startsWith("/") ? methodPath : "/" + methodPath);
        return apiPath.replaceAll("//", "/");
    }

    public static void getCxfEndpointPaths(JAXRSServerFactoryBean serverFactory, String contextPath,
        String serviceCode) {
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
                permissionVO.setIsOpenApi("N");
                // 方法名称
                permissionVO.setName(operationResource.getAnnotatedMethod().getName());
                // 方法请求类型
                permissionVO.setMethod(operationResource.getHttpMethod());
                // 方法路径
                String methodPath = operationResource.getURITemplate().getValue();
                String apiPath = buildApiPath(contextPath + "/services", endpointPath, servicePath, methodPath);
                permissionVO.setPath(apiPath);
                permissionVO.setType(apiPath.contains("/services/rcms/") ? "system" : "business");
                // 获取是否需要权限验证
                buildPermission(operationResource, permissionVO);
                permissionVO.setCreateBy("system");
                permissionVO.setUpdateBy("system");
                permissionVO.setCode(UUIDUtil.getUuid());
                permissionVO.setServiceCode(serviceCode);
                CURRENT_API_LIST.add(permissionVO);
            }
        }
    }
}
