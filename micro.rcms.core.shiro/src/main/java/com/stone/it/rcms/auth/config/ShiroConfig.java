package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.filter.RequestGlobalFilter;
import com.stone.it.rcms.auth.manager.RcmsWebSessionManager;
import java.util.Collections;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * org.springframework.beans.factory.annotation.Value
 * 
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
@Configuration
public class ShiroConfig {

    private static final String[] AUTHC_PATHS = {"/rcms/**"};
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
    private static final String[] ANON_PATHS = {"/user/login", "/user/refresh/login", "/user/register", "/user/token"};
    /**
     * 不需要认证授权的路径
     */
    @Value("rcms.auth.anon-paths")
    private List<String> anonPaths;
    /**
     * 需要认证授权的路径
     */
    @Value("rcms.auth.authc-paths")
    private List<String> authcPaths;

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        LOGGER.info("****** Shiro ShiroConfig shiroFilterFactoryBean...");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 注册自定义过滤器
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("logf", new RequestGlobalFilter());
        factoryBean.setFilters(filters);

        // 给filter设置安全管理
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> pathMaps = new LinkedHashMap<>();
        // 记录请求日志
        buildPathMaps(pathMaps, Collections.singletonList("/**"), "logf");
        // 构建动态配置的路径 (不需要认证)
        buildPathMaps(pathMaps, anonPaths, "anon");
        // 构建默认的路径 (不需要认证)
        buildPathMaps(pathMaps, List.of(ANON_PATHS), "anon");
        // 需要请求需要认证
        buildPathMaps(pathMaps, authcPaths, "authc");
        // 默认路径框架接口 (需要认证)
        buildPathMaps(pathMaps, List.of(AUTHC_PATHS), "authc");
        factoryBean.setFilterChainDefinitionMap(pathMaps);
        return factoryBean;
    }

    private void buildPathMaps(Map<String, String> pathMaps, List<String> paths, String filterKey) {
        if (paths != null && !paths.isEmpty()) {
            for (String path : paths) {
                pathMaps.put(path, filterKey);
            }
        }
    }

    // 自定义密码加密规则
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // hashedCredentialsMatcher.setHashIterations(2);
        // true 代表Hex编码，false代表采用base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    // 自定义认证
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCachingEnabled(false);
        return userRealm;
    }

    // 需要定义DefaultWebSecurityManager，否则会报bean冲突
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 创建认证对象 并设置认证策略
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        securityManager.setRealm(userRealm());
        // 修改web环境下的默认sessionManager
        DefaultWebSessionManager sessionManager = new RcmsWebSessionManager();
        // 60分钟(此设置会覆盖容器（tomcat）的会话过期时间设置)
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        securityManager.setSessionManager(sessionManager);
        // 禁用cookie来存sessionID(我们这里不用禁用，如果不传Token，则会使用原方式认证)
        sessionManager.setSessionIdCookieEnabled(false);
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

}
