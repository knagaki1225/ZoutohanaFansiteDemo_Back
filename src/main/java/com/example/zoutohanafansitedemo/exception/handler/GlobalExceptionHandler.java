package com.example.zoutohanafansitedemo.exception.handler;

import com.example.zoutohanafansitedemo.exception.InvalidPaginationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidPaginationException.class)
    public ResponseEntity<String> handleInvalidPaginationException(InvalidPaginationException e) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
