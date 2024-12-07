package com.alberto.workoutapp.services.exceptions;

public class BadRequestException extends RuntimeException {
    
    public BadRequestException(String msg) {
        super(msg);
    }
    
}
