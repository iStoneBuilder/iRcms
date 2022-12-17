package com.stone.it.micro.rcms.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/31 11:48 PM
 * @Version 1.0
 */
@Configuration
@ImportResource(locations = {"classpath*:config/*.config.xml"})
public class CxfConfigsConfig {

}
