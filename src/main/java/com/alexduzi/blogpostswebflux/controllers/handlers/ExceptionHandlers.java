package com.alexduzi.blogpostswebflux.controllers.handlers;

import com.alexduzi.blogpostswebflux.exceptions.ResourceNotFoundException;
import com.alexduzi.blogpostswebflux.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ResourceNotFoundException error, ServerHttpRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), error.getMessage(), error.getMessage(), request.getURI().getPath());

        return ResponseEntity.status(status).body(err);
    }
}
