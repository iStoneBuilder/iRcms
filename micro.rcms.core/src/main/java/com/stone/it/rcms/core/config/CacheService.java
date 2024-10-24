package com.stone.it.rcms.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/****
 * 
 * @author cj.stone*
 * @Date 2024/10/24*
 * @Desc
 */
@Service
public class CacheService {
    @Autowired
    private CacheManager cacheManager;

    public void addDataToCache(String cacheName, Object data) {
        Cache cache = cacheManager.getCache("data");
        if (cache != null) {
            cache.put(cacheName, data);
        }
    }

    public Object getDataFromCache(String cacheName) {
        Cache cache = cacheManager.getCache("data");
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(cacheName);
            if (valueWrapper != null) {
                return valueWrapper.get();
            }
        }
        return null;
    }
}
