package com.stone.it.micro.rcms.filter;

import com.alibaba.fastjson2.JSON;
import com.stone.it.micro.rcms.config.ReferConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */

@Slf4j
public class ReferFilter implements WebFilter {

  private final ReferConfiguration configuration;

  public ReferFilter(ReferConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    log.info("ReferFilter config " + JSON.toJSONString(configuration));
    return chain.filter(exchange);
  }
}
