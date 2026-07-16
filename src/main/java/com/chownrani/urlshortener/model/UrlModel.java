package com.chownrani.urlshortener.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "urls")
public class UrlModel {
    
    @Transient
    public static final String BASE_URL = "https://rani.com/";

    @Transient
    public static final String  SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;

    @Indexed(unique = true)
    private String shortCode;

    @Indexed(unique = true)
    private String shortUrl;

    private String originalUrl;

    private Instant createdAt;

    @Indexed(expireAfterSeconds = 0)
    private Instant expiresAt;
}
