package com.zemoso.springboot.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoSuchElementExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ElementErrorResponse> handleException(Exception e) {
        ElementErrorResponse elementErrorResponse = new ElementErrorResponse(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(elementErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
