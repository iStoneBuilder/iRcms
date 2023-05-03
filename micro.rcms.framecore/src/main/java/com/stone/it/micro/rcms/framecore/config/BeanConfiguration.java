package com.stone.it.micro.rcms.framecore.config;

import com.stone.it.micro.rcms.framecore.filter.JwtTokenFilter;
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
  public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilter() {
    final FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<JwtTokenFilter>();
    registrationBean.setFilter(new JwtTokenFilter());
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

}
