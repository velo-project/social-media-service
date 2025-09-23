package com.github.veloproject.socialmediaservices.domain.exceptions;

public class InvalidCommentProvidedException extends RuntimeException {
    public InvalidCommentProvidedException() {
        super("A comunidade fornecida é inválida.");
    }
}
