package com.stone.it.rcms.core.listener;

import com.stone.it.rcms.core.handler.PermissionHandler;
import java.util.ArrayList;
import java.util.HashSet;
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
        API_PATH_MOTHED_SET = new HashSet<>();
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
        LOGGER.info("ALL API SIZE {},{},{}", CURRENT_API_LIST.size(), AUTH_CODE_SET.size(), API_PATH_MOTHED_SET.size());
    }
}
