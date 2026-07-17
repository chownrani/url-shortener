package com.chownrani.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chownrani.urlshortener.model.UrlModel;

public interface UrlRepository extends MongoRepository<UrlModel, Long> {

    Optional<UrlModel> findByShortUrl(String shortUrl);
    Optional<UrlModel> findByOriginalUrl(String originalUrl);
}
