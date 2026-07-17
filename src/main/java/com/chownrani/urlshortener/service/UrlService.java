package com.chownrani.urlshortener.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chownrani.urlshortener.exception.UrlNotExistsException;
import com.chownrani.urlshortener.model.UrlModel;
import com.chownrani.urlshortener.model.utils.ShortCodeGenerator;
import com.chownrani.urlshortener.repository.UrlRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UrlService {

    private final UrlRepository repository;

    private final ShortCodeGenerator shortCodeGenerator;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Value("${base.url}")
    private String baseUrl;

    public UrlModel create(UrlModel urlModel) {
        return repository.findByOriginalUrl(urlModel.getOriginalUrl())
            .orElseGet(() -> {
                if (urlModel.getId() == null || urlModel.getId() < 1) {
                    urlModel.setId(
                        sequenceGeneratorService.generateSequence(UrlModel.SEQUENCE_NAME)
                    );
                }

                String shortCode = createShortCode(urlModel.getId());
                urlModel.setShortCode(shortCode);
                urlModel.setShortUrl(baseUrl + shortCode);
                return repository.save(urlModel);
            });
    }

    public String getOriginalUrlByShortUrl(String shortUrl) {
        return repository
            .findByShortUrl(shortUrl)
            .map(UrlModel::getOriginalUrl)
            .orElseThrow(() -> new UrlNotExistsException());
    }

    public String getShortUrl(UrlModel originalUrl) {
        String shortCode = createShortCode(originalUrl.getId());

        return baseUrl + shortCode;
    }

    public String createShortCode(Long urlSeq) {
        return shortCodeGenerator.createShortCode(urlSeq);
    }

}
