package com.SpringBoot.Drop_Tracking_JWT.exception;

public class DeviceNotConnected extends RuntimeException {
    public DeviceNotConnected(String message) {
        super(message);
    }
}
