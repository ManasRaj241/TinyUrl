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
        System.out.println("Attempting to resolve: " + shortId.trim());
        String result = redisTemplate.opsForValue().get(shortId.trim());
        System.out.println("Resolution result: " + result);
        return result;
    }
}

