package com.chownrani.urlshortener.model.utils;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShortCodeGenerator {

    private final Hashids hashids;

    public ShortCodeGenerator(@Value("${SECRET_KEY}") String secretKey) {
        if (secretKey == null || secretKey.isBlank()) {
            throw new IllegalArgumentException("SECRET_KEY must not be blank");
        }
        this.hashids = new Hashids(secretKey, 6);
    }

    public String createShortCode(Long urlSeq) {
        if (urlSeq == null) {
            throw new IllegalArgumentException("urlSeq must not be null");
        }
        if (urlSeq < 0) {
            throw new IllegalArgumentException("urlSeq must be greater than or equal to zero");
        }
        return hashids.encode(urlSeq);
    }
}
