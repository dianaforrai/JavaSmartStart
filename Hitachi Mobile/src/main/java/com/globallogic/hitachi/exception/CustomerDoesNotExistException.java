package com.globallogic.hitachi.exception;

public class CustomerDoesNotExistException extends Exception {

    public CustomerDoesNotExistException() {
        super("Customer not found");
    }

    public CustomerDoesNotExistException(String message) {
        super(message);
    }

    public CustomerDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}