package com.bhawna.SpringBoot.Testing.TestingApplication.advices;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus status;

    public ApiError(){
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String message, HttpStatus status) {
        this();
        this.message = message;
        this.status = status;
    }
}
