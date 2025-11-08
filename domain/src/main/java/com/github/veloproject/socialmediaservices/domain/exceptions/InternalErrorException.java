package com.github.veloproject.socialmediaservices.domain.exceptions;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String message) {
        super(message);
    }
}
