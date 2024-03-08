package com.stone.it.rcms.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
@Configuration
@ImportResource(locations = {"classpath*:config/*.config.xml","META-INF/cxf/cxf.xml"})
public class CxfConfigsConfig {

}
