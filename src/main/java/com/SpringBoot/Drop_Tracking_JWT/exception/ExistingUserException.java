package com.SpringBoot.Drop_Tracking_JWT.exception;

public class ExistingUserException extends RuntimeException {
    public ExistingUserException(String message) {
        super(message);
    }
}
