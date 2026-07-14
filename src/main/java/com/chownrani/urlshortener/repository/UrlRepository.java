package com.chownrani.urlshortener.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.chownrani.urlshortener.model.UrlModel;

public interface UrlRepository extends MongoRepository<UrlModel, ObjectId> {
}
