package com.bhawna.SpringBoot.Testing.TestingApplication.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
