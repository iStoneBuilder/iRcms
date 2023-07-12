package com.stone.it.micro.rcms.framedata.config;

import com.stone.it.micro.rcms.framedata.interceptor.PageHelperInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 增加mybatis 插件
 * @author cj.stone
 * @Date 2023/7/11
 * @Desc
 */
@Configuration
public class MyBatisPageConfig {

  @Bean
  public ConfigurationCustomizer configurationCustomizer(){
    return configuration -> {
      PageHelperInterceptor pageHelper = new PageHelperInterceptor();
      configuration.addInterceptor(pageHelper);
    };
  }

}
