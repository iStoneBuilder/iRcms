package com.stone.it.micro.rcms.framecore.config;

import com.stone.it.micro.rcms.framecore.filter.LogSpanFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cj.stone
 * @Date 2023/5/1
 * @Desc
 */
@Configuration
public class BeanConfiguration {

  @Bean
  public FilterRegistrationBean<LogSpanFilter> logSpanFilter() {
    final FilterRegistrationBean<LogSpanFilter> registrationBean = new FilterRegistrationBean<LogSpanFilter>();
    registrationBean.setFilter(new LogSpanFilter());
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

}
