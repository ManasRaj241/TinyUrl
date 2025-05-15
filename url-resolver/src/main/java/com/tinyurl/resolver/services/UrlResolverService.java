package com.tinyurl.resolver.services;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlResolverService {
    private final StringRedisTemplate redisTemplate;

    public UrlResolverService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String resolve(String shortId) {
        return redisTemplate.opsForValue().get(shortId);
    }
}

