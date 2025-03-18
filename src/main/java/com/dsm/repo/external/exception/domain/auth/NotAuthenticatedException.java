package com.dsm.repo.external.exception.domain.auth;

public class NotAuthenticatedException extends RuntimeException {

    public NotAuthenticatedException(String message) {
        super(message);
    }
}