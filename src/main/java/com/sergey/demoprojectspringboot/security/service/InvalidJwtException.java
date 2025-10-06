package com.sergey.demoprojectspringboot.security.service;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(String message) {
        super(message);
    }
}
