package com.chownrani.urlshortener.service;

import org.springframework.stereotype.Service;

import com.chownrani.urlshortener.model.UrlModel;
import com.chownrani.urlshortener.model.utils.ShortCodeGenerator;
import com.chownrani.urlshortener.repository.UrlRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UrlService {

    private final UrlRepository repository;
    private final ShortCodeGenerator shortCodeGenerator;

    public String getShortUrl(UrlModel originalUrl) {
        String shortCode = createShortCode(originalUrl.getId());
        
        return UrlModel.BASE_URL + shortCode;
    }

    public String getOriginalUrlByShortUrl(String shortUrl) {
        return repository.getOriginalUrlByShortUrl(shortUrl).getOriginalUrl();
    }

    public String createShortCode(Long urlSeq) {
        return shortCodeGenerator.createShortCode(urlSeq);
    }

}
