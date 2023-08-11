package com.stone.it.rcms.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置静态访问资源
 * @author cj.stone
 * @Date 2023/7/23
 * @Desc
 */
@Configuration
public class ResourceAdapter extends WebMvcConfigurerAdapter {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/web/**")
        .addResourceLocations("classpath:/web/");
    super.addResourceHandlers(registry);
  }
}
