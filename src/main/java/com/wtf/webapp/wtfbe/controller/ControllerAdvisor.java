package com.wtf.webapp.wtfbe.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleException(IOException e) {
        return new ResponseEntity<>(e.getMessage(), EXPECTATION_FAILED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), EXPECTATION_FAILED);
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<String> handleException(ClassNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), EXPECTATION_FAILED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), EXPECTATION_FAILED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), EXPECTATION_FAILED);
    }
}
