package com.stone.it.rcms.web.config;


import com.stone.it.rcms.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加登陆拦截器
 * @author cj.stone
 * @Date 2023/7/22
 * @Desc
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
    // 所有路径都被拦截
    registration.addPathPatterns("**/**");
    // 添加不拦截路径
    registration.excludePathPatterns( "**/","**/static/**","**/web/**");
  }

}
