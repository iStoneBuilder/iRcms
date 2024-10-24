package com.stone.it.rcms.core.config;

import java.util.concurrent.TimeUnit;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

/***
 * 设置缓存配置**
 * 
 * @author cj.stone*
 * @Date 2024/10/24*
 * @Desc
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    @Bean
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder().maximumSize(100) // 最大缓存条目数
            .expireAfterWrite(1, TimeUnit.MINUTES) // 写入后1分钟过期
            .weakKeys(); // 使用弱引用存储键
    }
}
