package com.stone.it.micro.rcms.web.config;


import com.stone.it.micro.rcms.web.filter.WebResourceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册资源加载器
 * @author cj.stone
 * @Date 2023/7/22
 * @Desc
 */
@Configuration
public class FilterConfiguration {

  @Bean
  public FilterRegistrationBean<WebResourceFilter> webResourceFilter() {
    final FilterRegistrationBean<WebResourceFilter> registrationBean = new FilterRegistrationBean<WebResourceFilter>();
    registrationBean.setFilter(new WebResourceFilter());
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(2);
    return registrationBean;
  }
}
