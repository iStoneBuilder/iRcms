package com.stone.it.rcms.core.config;

import com.stone.it.rcms.core.interceptor.PageHelperInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 增加mybatis 插件
 * @author cj.stone
 * @Desc
 */
@Configuration
public class MyBatisPageConfig {

  @Bean
  public ConfigurationCustomizer configurationCustomizer(){
    return configuration -> {
      // 分页插件件
      configuration.addInterceptor(new PageHelperInterceptor());
    };
  }

}
