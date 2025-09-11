package com.github.veloproject.socialmediaservices.application.queries.search_by_user.handler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
