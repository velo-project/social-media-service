package com.github.veloproject.socialmediaservices.presentation.advicers;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidPostProvidedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidPostProvidedExceptionHandler {
    @ExceptionHandler(InvalidPostProvidedException.class)
    public ResponseEntity<Response> handleException(InvalidPostProvidedException e) {
        int responseStatus = 400;
        return ResponseEntity
                .status(responseStatus)
                .body(new Response(responseStatus, e.getMessage()));
    }
}
