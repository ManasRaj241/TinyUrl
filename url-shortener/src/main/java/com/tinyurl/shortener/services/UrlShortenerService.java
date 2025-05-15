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
        System.out.println("Storing: " + shortId.trim() + " -> " + longUrl);
        redisTemplate.opsForValue().set(shortId.trim(), longUrl);

        // Verify storage
        String retrieved = redisTemplate.opsForValue().get(shortId.trim());
        System.out.println("Verification after storing: " + shortId.trim() + " -> " + retrieved);

        return shortId.trim();
    }
}
