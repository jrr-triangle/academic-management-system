package com.jrrtriangle.ams.exception;

public class EmptyException extends Exception{
    public EmptyException() {
        super();
    }

    public EmptyException(String message) {
        super(message);
    }

    public EmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyException(Throwable cause) {
        super(cause);
    }

    protected EmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
