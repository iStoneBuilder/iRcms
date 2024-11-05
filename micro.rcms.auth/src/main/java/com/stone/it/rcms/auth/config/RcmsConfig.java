package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.filter.HeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cj.stone
 * @Date 2024/11/5
 * @Desc
 */
@Configuration
public class RcmsConfig {
    @Bean
    public FilterRegistrationBean<HeaderFilter> headerFilter() {
        FilterRegistrationBean<HeaderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HeaderFilter());
        registrationBean.addUrlPatterns("/*"); // 适用于所有请求
        return registrationBean;
    }
}
