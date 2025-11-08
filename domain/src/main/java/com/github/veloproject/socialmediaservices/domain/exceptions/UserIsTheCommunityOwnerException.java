package com.github.veloproject.socialmediaservices.domain.exceptions;

public class UserIsTheCommunityOwnerException extends RuntimeException {
    public UserIsTheCommunityOwnerException() {
        super("O usuário é o dono da comunidade e não pode sair dela.");
    }
}
