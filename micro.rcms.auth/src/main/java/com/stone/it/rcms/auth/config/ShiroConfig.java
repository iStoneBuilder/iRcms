package com.stone.it.rcms.auth.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
@Configuration
public class ShiroConfig {
  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
  {
    ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
    //给filter设置安全管理
    factoryBean.setSecurityManager(securityManager);
    //配置系统的受限资源
    Map<String,String> map = new HashMap<>();
    //登录请求无需认证
    map.put("/login", "anon");
    //其他请求需要认证
    map.put("/**", "authc");
    //访问需要认证的页面如果未登录会跳转到/login
    factoryBean.setLoginUrl("/login");
    //访问未授权页面会自动跳转到/unAuth
    factoryBean.setUnauthorizedUrl("/unAuth");
    factoryBean.setFilterChainDefinitionMap(map);
    return factoryBean;
  }
}
