package com.ronial.cache.impl;

import com.ronial.cache.RedisDistributeService;
import com.ronial.exceptions.InfrastructureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisDistributeServiceImpl implements RedisDistributeService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisDistributeServiceImpl(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void release(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void refresh(String key, long ttl) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (value == null) {
            throw new InfrastructureException(this.getClass(), "Error while refreshing value");
        }
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttl));
    }

    @Override
    public boolean acquire(String key, String value, long ttl) {
        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(key, value, Duration.ofSeconds(ttl));
        return success != null && success;
    }
}
