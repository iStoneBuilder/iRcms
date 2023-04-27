package com.stone.it.micro.rcms.config;

import com.stone.it.micro.rcms.filter.RefererFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */
@Configuration
public class BeanConfiguration {

  @Bean
  @Order(50)
  public RefererFilter refererFilter(RefererConfiguration configuration) {
    return new RefererFilter(configuration);
  }

}
