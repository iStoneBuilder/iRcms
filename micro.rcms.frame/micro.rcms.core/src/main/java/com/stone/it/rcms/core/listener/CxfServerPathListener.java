package com.stone.it.rcms.core.listener;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.handler.PermissionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.stone.it.rcms.core.http.RequestUtil;
import com.stone.it.rcms.core.http.ResponseEntity;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 启动注册接口
 * 
 * @author cj.stone
 * @Date 2024/11/26
 * @Desc
 */
@Component
public class CxfServerPathListener extends PermissionHandler implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CxfServerPathListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        CURRENT_API_LIST = new ArrayList<>();
        AUTH_CODE_SET = new HashSet<>();
        ApplicationContext context = event.getApplicationContext();
        // 获取服务跟路径
        String contextPath = context.getEnvironment().getProperty("server.servlet.context-path");
        // 服务提供商
        String serviceCode = context.getEnvironment().getProperty("spring.application.name");
        // 获取所有配置的接口暴露
        String[] beanNames = context.getBeanNamesForType(JAXRSServerFactoryBean.class);
        for (String beanName : beanNames) {
            getCxfEndpointPaths(context.getBean(beanName, JAXRSServerFactoryBean.class), contextPath, serviceCode);
        }
        LOGGER.info("ALL API SIZE {},{}", CURRENT_API_LIST.size(), AUTH_CODE_SET.size());
        // 判断是否开启注册接口
        String apiPath = context.getEnvironment().getProperty("rcms.api.register.service.path");
        String appTokenPath = context.getEnvironment().getProperty("rcms.api.register.token.path");
        String app = context.getEnvironment().getProperty("rcms.api.register.app");
        String appSecret = context.getEnvironment().getProperty("rcms.api.register.secret");
        LOGGER.info("...{},{},{},{}", apiPath, appTokenPath, app, appSecret);
        if (apiPath != null && appTokenPath != null && app != null && appSecret != null) {
            JSONObject body = new JSONObject();
            body.put("appId", app);
            body.put("secret", appSecret);
            ResponseEntity response = RequestUtil.doPost(appTokenPath, JSONObject.toJSONString(body));
            if ("200".equals(response.getCode()) && response.getBody() != null && !response.getBody().isEmpty()) {
                String token =
                    JSONObject.parseObject(response.getBody()).getJSONObject("data").getString("Authorization");
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", token);
                RequestUtil.doPost(apiPath, JSONObject.toJSONString(CURRENT_API_LIST), header);
            }
        }
    }
}