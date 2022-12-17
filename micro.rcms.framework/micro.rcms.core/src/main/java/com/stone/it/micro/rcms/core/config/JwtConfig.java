package com.stone.it.micro.rcms.core.config;

import com.stone.it.micro.rcms.core.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/30 8:58 PM
 * @Version 1.0
 */
@Configuration
public class JwtConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<JwtFilter>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }


}
