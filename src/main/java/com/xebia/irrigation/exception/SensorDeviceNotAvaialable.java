package com.xebia.irrigation.exception;

public class SensorDeviceNotAvaialable extends Exception {
    String msg;
    public SensorDeviceNotAvaialable(String message)
    {
        this.msg=message;
    }
}
