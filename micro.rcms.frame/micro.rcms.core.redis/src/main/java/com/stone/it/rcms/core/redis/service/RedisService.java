package com.stone.it.rcms.core.redis.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author cj.stone
 * @Date 2025/1/11
 * @Desc
 */
@Service
public class RedisService {

    private static final String DEF_LOCK_KEY = "defLock:";
    /**
     * 默认缓存时间（单位：s）
     */
    private static final long DEF_EXPIRE = 3600 * 2;

    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public boolean isLock(String requestId, long expire) {
        return isLock(DEF_LOCK_KEY, requestId, expire);
    }

    public boolean isLock(String lockKey, String requestId, long expire) {
        return isLock(lockKey, requestId, expire, TimeUnit.SECONDS);
    }

    public boolean isLock(String lockKey, String requestId, long expire, TimeUnit timeUnit) {
        try {
            RedisCallback<Boolean> callBack = (connect) -> {
                return connect.set(lockKey.getBytes(StandardCharsets.UTF_8), requestId.getBytes(StandardCharsets.UTF_8),
                    Expiration.seconds(timeUnit.toSeconds(expire)), RedisStringCommands.SetOption.SET_IF_ABSENT);
            };
            return Boolean.TRUE.equals(redisTemplate.execute(callBack));
        } catch (Exception e) {
            logger.error("获取锁异常：{}", e.getMessage());
        }
        return false;
    }

    public boolean delLock(String requestId) {
        return delLock(DEF_LOCK_KEY, requestId);
    }

    public boolean delLock(String lockKey, String requestId) {
        String script =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisCallback<Boolean> callBack = (connection) -> {
            return connection.eval(script.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(StandardCharsets.UTF_8),
                requestId.getBytes(StandardCharsets.UTF_8));
        };
        return Boolean.TRUE.equals(redisTemplate.execute(callBack));
    }

    public String getLockVal() {
        return getLockVal(DEF_LOCK_KEY);
    }

    public String getLockVal(String lockKey) {
        try {
            RedisCallback<String> callBack = (connection) -> {
                return new String(Objects.requireNonNull(connection.get(lockKey.getBytes())), StandardCharsets.UTF_8);

            };
            return redisTemplate.execute(callBack);
        } catch (Exception e) {
            logger.error("获取锁值异常：{}", e.getMessage());
        }
        return null;
    }

    public void set(String key, Object val) {
        set(key, val, DEF_EXPIRE);
    }

    public void set(String key, Object val, long second) {
        if (StringUtils.isNotBlank(key) && val != null) {
            if (second > 0) {
                redisTemplate.opsForValue().set(key, val, second, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, val);
            }
        }
    }

    public void setDay(String key, Object val, long days) {
        if (StringUtils.isNotBlank(key) && val != null) {
            if (days > 0) {
                redisTemplate.opsForValue().set(key, val, days, TimeUnit.DAYS);
            } else {
                redisTemplate.opsForValue().set(key, val);
            }
        }
    }
}
