package com.tinyurl.resolver.controller;

import com.tinyurl.resolver.services.UrlResolverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UrlResolverController {
    private final UrlResolverService service;

    public UrlResolverController(UrlResolverService service) {
        this.service = service;
    }

    @GetMapping("/{shortId}")
    public ResponseEntity<String> resolve(@PathVariable String shortId) {
        String longUrl = service.resolve(shortId);
        if (longUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Original URL: "+longUrl);
    }
}

