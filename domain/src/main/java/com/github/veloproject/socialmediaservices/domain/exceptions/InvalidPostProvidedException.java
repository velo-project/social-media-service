package com.github.veloproject.socialmediaservices.domain.exceptions;

public class InvalidPostProvidedException extends RuntimeException {
    public InvalidPostProvidedException() {
        super("O post fornecido é inválido.");
    }
}
