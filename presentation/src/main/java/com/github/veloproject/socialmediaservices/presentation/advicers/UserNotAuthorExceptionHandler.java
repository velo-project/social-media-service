package com.github.veloproject.socialmediaservices.presentation.advicers;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserNotAuthorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserNotAuthorExceptionHandler {
    @ExceptionHandler(UserNotAuthorException.class)
    public ResponseEntity<Response> handleException(UserNotAuthorException e) {
        int responseStatus = 403;
        return ResponseEntity
                .status(responseStatus)
                .body(new Response(responseStatus, e.getMessage()));
    }
}
