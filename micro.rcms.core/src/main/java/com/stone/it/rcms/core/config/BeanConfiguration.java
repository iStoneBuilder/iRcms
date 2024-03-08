package com.stone.it.rcms.core.config;

import com.stone.it.rcms.core.filter.LogSpanFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Configuration
public class BeanConfiguration {

  @Bean
  public FilterRegistrationBean<LogSpanFilter> logSpanFilter() {
    final FilterRegistrationBean<LogSpanFilter> registrationBean = new FilterRegistrationBean<LogSpanFilter>();
    registrationBean.setFilter(new LogSpanFilter());
    registrationBean.setOrder(1);
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }

}
