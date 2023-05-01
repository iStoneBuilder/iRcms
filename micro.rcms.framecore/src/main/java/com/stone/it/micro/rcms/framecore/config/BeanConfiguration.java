package com.stone.it.micro.rcms.framecore.config;

import com.stone.it.micro.rcms.framecore.filter.JwtFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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
  @ConditionalOnExpression("security.gwt.auth.enabled'=='true'")
  public FilterRegistrationBean<JwtFilter> jwtFilter() {
    final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<JwtFilter>();
    registrationBean.setFilter(new JwtFilter());
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

}
