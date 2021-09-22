package com.jrrtriangle.ams.exception;

public class ValidationExceptionHandler extends Exception{
    public ValidationExceptionHandler() {
        super();
    }

    public ValidationExceptionHandler(String message) {
        super(message);
    }

    public ValidationExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationExceptionHandler(Throwable cause) {
        super(cause);
    }

    protected ValidationExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
