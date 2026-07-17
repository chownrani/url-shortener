package com.chownrani.urlshortener.controller;

import org.springframework.web.bind.annotation.RestController;

import com.chownrani.urlshortener.model.UrlModel;
import com.chownrani.urlshortener.model.dto.UrlRequest;
import com.chownrani.urlshortener.service.UrlService;

import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequiredArgsConstructor
@RestController
@RequestMapping("/aki")
public class UrlController {
    
    private final UrlService service;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        return ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(service.getOriginalUrlByShortUrl(shortUrl)))
            .build();
    }

    @PostMapping("/")
    public ResponseEntity<UrlModel> createShortUrl(@RequestBody UrlRequest dto) {
        UrlModel createdUrl = service.create(new UrlModel(dto.originalUrl()));

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUrl);
    }
    
}
