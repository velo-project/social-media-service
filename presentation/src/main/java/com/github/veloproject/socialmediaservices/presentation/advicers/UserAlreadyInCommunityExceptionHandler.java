package com.github.veloproject.socialmediaservices.presentation.advicers;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserAlreadyInCommunityException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserAlreadyInCommunityExceptionHandler {
    @ExceptionHandler(UserAlreadyInCommunityException.class)
    public ResponseEntity<Response> handleException(UserAlreadyInCommunityException e) {
        int responseStatus = 409;
        return ResponseEntity
                .status(responseStatus)
                .body(new Response(responseStatus, e.getMessage()));
    }
}
