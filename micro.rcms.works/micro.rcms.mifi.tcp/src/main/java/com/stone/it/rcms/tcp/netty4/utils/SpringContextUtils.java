package com.stone.it.rcms.tcp.netty4.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Date 2025/1/10
 * @Desc
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public SpringContextUtils() {}

    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    private static void assertApplicationContext() {
        if (applicationContext == null) {
            throw new RuntimeException(
                "applicaitonContext\u5c5e\u6027\u4e3anull,\u8bf7\u68c0\u67e5\u662f\u5426\u6ce8\u5165\u4e86SpringContextHolder!");
        }
    }
}
