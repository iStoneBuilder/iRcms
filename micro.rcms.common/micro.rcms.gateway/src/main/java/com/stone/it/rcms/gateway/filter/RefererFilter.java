package com.stone.it.rcms.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.stone.it.rcms.gateway.config.RefererConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * RefererFilter
 * 
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */
public class RefererFilter implements WebFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefererFilter.class);

    private final RefererConfiguration configuration;

    /**
     * RefererFilter
     * 
     * @param configuration configuration
     */
    public RefererFilter(RefererConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        LOGGER.info("ReferFilter config {}", JSON.toJSONString(configuration));
        // 获取域名
        String domain = exchange.getRequest().getURI().getHost();
        // 放过白名单
        if (!configuration.getWhiteList().isEmpty()) {
            for (String whiteHost : configuration.getWhiteList()) {
                if (domain.endsWith(whiteHost)) {
                    return chain.filter(exchange);
                }
            }
        }
        // 排除忽略的域名
        if (!configuration.getIgnoreHosts().isEmpty()) {
            for (String ignoredHost : configuration.getIgnoreHosts()) {
                if (domain.equals(ignoredHost)) {
                    return chain.filter(exchange);
                }
            }
        }
        return chain.filter(exchange);
    }
}
