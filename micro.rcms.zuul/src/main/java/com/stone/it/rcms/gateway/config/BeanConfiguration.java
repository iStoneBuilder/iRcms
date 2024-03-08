package com.stone.it.rcms.gateway.config;

import com.stone.it.rcms.gateway.filter.RefererFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */
@Configuration
public class BeanConfiguration {

  /**
   * 设置允许跨越请求
   *
   * @return
   */
  @Bean
  public CorsWebFilter corsWebFilter() {
    CorsConfiguration config = new CorsConfiguration();
    // 允许任何请求头
    config.addAllowedHeader("*");
    // 允许任何方法
    config.addAllowedMethod("*");
    // 允许任何域名
    config.addAllowedOrigin("*");
    // 允许接受cookie
    config.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(
        new PathPatternParser());
    source.registerCorsConfiguration("/**", config);
    return new CorsWebFilter(source);
  }

  /**
   * 自定义校验Refer
   *
   * @param configuration
   * @return
   */
  @Bean
  @Order(50)
  @ConditionalOnExpression("'security.gw.referer.enabled'=='true'")
  public RefererFilter refererFilter(RefererConfiguration configuration) {
    return new RefererFilter(configuration);
  }

}
