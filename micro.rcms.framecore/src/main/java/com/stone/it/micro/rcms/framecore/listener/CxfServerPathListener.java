package com.stone.it.micro.rcms.framecore.listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Set;
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
 *
 * @author cj.stone
 * @Date 2023/5/10
 * @Desc
 */

@Component
public class CxfServerPathListener implements ApplicationListener<ContextRefreshedEvent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(CxfServerPathListener.class);

  private static final JSONArray allApiServerInfo = new JSONArray();

  private static void getCXFEndpointPaths(JAXRSServerFactoryBean serverFactory,String contextPath) {
    // jaxrs:server 接口暴露配置的路径
    String endpointPath = serverFactory.getAddress();
    // 获取接口暴露下的service
    List<ClassResourceInfo> classResources = serverFactory.getServiceFactory()
        .getClassResourceInfo();
    // 循环处理service 接口方法
    for (ClassResourceInfo classResource : classResources ) {
      // service 总接口路径
      String servicePath = classResource.getURITemplate().getValue();
      // 获取接口所有方法
      Set<OperationResourceInfo> opera = classResource.getMethodDispatcher().getOperationResourceInfos();
      for (OperationResourceInfo operationResource : opera ) {
        JSONObject iApiServerInfo = new JSONObject();
        // 方法名称
        iApiServerInfo.put("api_name", operationResource.getAnnotatedMethod().getName());
        // 方法请求类型
        iApiServerInfo.put("method_type", operationResource.getHttpMethod());
        // 方法路径
        String methodPath = operationResource.getURITemplate().getValue();
        iApiServerInfo.put("api_path", buildApiPath(contextPath,endpointPath,servicePath,methodPath));
        LOGGER.info("RCMS cxf api services path : " + iApiServerInfo.getString("api_path")+" | " + iApiServerInfo.getString("method_type"));
        // 存储所有服务信息
        allApiServerInfo.add(iApiServerInfo);
      }
    }
  }

  private static String buildApiPath(String contextPath,String endpointPath, String servicePath, String methodPath) {
    String apiPath = contextPath + (endpointPath.startsWith("/")? endpointPath : "/"+endpointPath);
    apiPath = apiPath + (servicePath.startsWith("/")? servicePath:"/"+servicePath);
    apiPath = apiPath + (methodPath.startsWith("/") ? methodPath : "/"+methodPath);
    return apiPath.replaceAll("//","/");
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    LOGGER.info("RCMS cxf api services scan start.");
    ApplicationContext context = event.getApplicationContext();
    // 获取服务跟路径
    String contextPath = context.getEnvironment().getProperty("server.servlet.context-path");
    // 获取所有配置的接口暴露
    String[] beanNames = context.getBeanNamesForType(JAXRSServerFactoryBean.class);
    // 循环处理 jaxrs:server
    for (String beanName : beanNames) {
      getCXFEndpointPaths(context.getBean(beanName, JAXRSServerFactoryBean.class),contextPath);
    }
    LOGGER.info("RCMS cxf api services count : " + allApiServerInfo.size());
  }

  public JSONArray getAllApiServerInfo() {
    return allApiServerInfo;
  }
}
