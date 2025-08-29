package com.globallogic.hitachi.exception;

public class SIMDoesNotExistException extends Exception {

    public SIMDoesNotExistException() {
        super("SIM details not found");
    }

    public SIMDoesNotExistException(String message) {
        super(message);
    }

    public SIMDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}