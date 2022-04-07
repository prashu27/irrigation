package com.xebia.irrigation.exception;

public class ErrorMessage {
    int status;
    String errMessage;
    String description;

    ErrorMessage(int status, String errMessage, String description) {
        this.status = status;
        this.errMessage = errMessage;
        this.description = description;
    }
}

