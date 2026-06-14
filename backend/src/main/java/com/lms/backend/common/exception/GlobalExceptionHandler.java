package com.lms.backend.common.exception;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public Map<String, String> handleEmailExists(EmailAlreadyExistsException ex) {
        return Map.of(
            "message",
            ex.getMessage()
        );
    }

    @ExceptionHandler(
            ResourceNotFoundException.class)
    public Map<String,String> handleNotFound(
            ResourceNotFoundException ex){

        return Map.of(
                "message",
                ex.getMessage()
        );
    }

    @ExceptionHandler(
            InvalidCredentialsException.class)
    public Map<String,String> handleInvalidCredentials(
            InvalidCredentialsException ex){

        return Map.of(
                "message",
                ex.getMessage()
        );
    }
}
