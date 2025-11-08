package com.github.veloproject.socialmediaservices.infrastructure.services.image.exceptions;

public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException(String message) {
        super("Image must be jpg, jpeg or png.");
    }
}
