package com.SpringBoot.Drop_Tracking_JWT.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
