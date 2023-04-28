package com.stone.it.micro.rcms.filter;

import com.alibaba.fastjson2.JSON;
import com.stone.it.micro.rcms.config.RefererConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * RefererFilter
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */

@Slf4j
public class RefererFilter implements WebFilter {

  private final RefererConfiguration configuration;

  /**
   * RefererFilter
   * @param configuration configuration
   */
  public RefererFilter(RefererConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    log.info("ReferFilter config " + JSON.toJSONString(configuration));
    // 获取域名
    String domain = exchange.getRequest().getURI().getHost();
    if(configuration.getWhiteList().size()>0){

    }
    return chain.filter(exchange);
  }
}
