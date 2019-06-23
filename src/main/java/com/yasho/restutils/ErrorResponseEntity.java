package com.yasho.restutils;

public class ErrorResponseEntity {
    private int errorCode;
    private String errorMessage;

    public ErrorResponseEntity(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
