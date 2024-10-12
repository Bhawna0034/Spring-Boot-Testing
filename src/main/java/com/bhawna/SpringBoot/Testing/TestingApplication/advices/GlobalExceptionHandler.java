package com.bhawna.SpringBoot.Testing.TestingApplication.advices;

import com.bhawna.SpringBoot.Testing.TestingApplication.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception){
//        ApiError apiError = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.internalServerError().build();
    }
}
