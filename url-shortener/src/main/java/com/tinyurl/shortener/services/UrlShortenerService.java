package com.tinyurl.shortener.services;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlShortenerService {
    private final StringRedisTemplate redisTemplate;

    public UrlShortenerService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String shortenUrl(String longUrl) {
        String shortId = UUID.randomUUID().toString().substring(0, 6);
        redisTemplate.opsForValue().set(shortId, longUrl);
        return shortId;
    }
}
