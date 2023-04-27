package com.stone.it.micro.rcms.filter;

import com.stone.it.micro.rcms.config.ReferConfiguration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */
public class ReferFilter implements WebFilter {

  private ReferConfiguration configuration;

  public ReferFilter(ReferConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return chain.filter(exchange);
  }
}
