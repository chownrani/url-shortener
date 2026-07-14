package com.chownrani.urlshortener.model;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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
    
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String shortCode;

    private String originalUrl;

    private Instant createdAt;

    @Indexed(expireAfterSeconds = 0)
    private Instant expiresAt;
}
