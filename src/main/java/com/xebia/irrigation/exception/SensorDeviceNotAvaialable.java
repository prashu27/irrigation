package com.xebia.irrigation.exception;

public class SensorDeviceNotAvaialable extends Exception {
   private String errorMsg;

    public SensorDeviceNotAvaialable(String message) {
        this.errorMsg = message;
    }
}
