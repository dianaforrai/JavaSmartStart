package com.globallogic.hitachi.exception;

public class CustomerTableEmptyException extends Exception {

    public CustomerTableEmptyException() {
        super("No data found in customer table");
    }

    public CustomerTableEmptyException(String message) {
        super(message);
    }

    public CustomerTableEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}