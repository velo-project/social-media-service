package com.github.veloproject.socialmediaservices.presentation.advicers;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserIsTheCommunityOwnerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserIsTheCommunityOwnerExceptionHandler {
    @ExceptionHandler(UserIsTheCommunityOwnerException.class)
    public ResponseEntity<Response> handleException(UserIsTheCommunityOwnerException e) {
        int responseStatus = 403;
        return ResponseEntity
                .status(responseStatus)
                .body(new Response(responseStatus, e.getMessage()));
    }
}
