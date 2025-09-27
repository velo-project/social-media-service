package com.github.veloproject.socialmediaservices.domain.exceptions;

public class UserNotInCommunityException extends RuntimeException {
    public UserNotInCommunityException() {
        super("O usuário não está presente na comunidade fornecida.");
    }
}
