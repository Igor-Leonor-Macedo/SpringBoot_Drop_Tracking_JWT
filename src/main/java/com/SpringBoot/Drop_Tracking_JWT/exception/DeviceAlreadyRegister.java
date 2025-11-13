package com.SpringBoot.Drop_Tracking_JWT.exception;

public class DeviceAlreadyRegister extends RuntimeException {
    public DeviceAlreadyRegister(String message) {
        super(message);
    }
}
