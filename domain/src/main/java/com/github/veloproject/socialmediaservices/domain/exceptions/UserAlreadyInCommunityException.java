package com.github.veloproject.socialmediaservices.domain.exceptions;

public class UserAlreadyInCommunityException extends RuntimeException {
    public UserAlreadyInCommunityException() {
        super("O usuário já está presente na comunidade fornecida.");
    }
}
