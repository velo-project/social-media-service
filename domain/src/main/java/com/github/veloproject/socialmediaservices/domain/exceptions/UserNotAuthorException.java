package com.github.veloproject.socialmediaservices.domain.exceptions;

public class UserNotAuthorException extends RuntimeException {
    public UserNotAuthorException() {
        super("Usuário não autorizado a modificar este post.");
    }
}
