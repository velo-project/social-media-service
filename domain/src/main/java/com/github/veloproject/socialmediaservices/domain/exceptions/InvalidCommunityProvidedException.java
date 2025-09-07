package com.github.veloproject.socialmediaservices.domain.exceptions;

public class InvalidCommunityProvidedException extends RuntimeException {
    public InvalidCommunityProvidedException() {
        super("A comunidade fornecida é inválida.");
    }
}
