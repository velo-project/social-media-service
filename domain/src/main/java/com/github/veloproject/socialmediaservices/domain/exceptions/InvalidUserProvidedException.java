package com.github.veloproject.socialmediaservices.domain.exceptions;

public class InvalidUserProvidedException extends RuntimeException {
    public InvalidUserProvidedException() {
        super("O usuário fornecido é inválido.");
    }
}
