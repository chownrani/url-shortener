package com.chownrani.urlshortener.exception;

public class UrlNotExistsException extends RuntimeException{
    public UrlNotExistsException() {
        super("This URL does not exists.");
    }
}
