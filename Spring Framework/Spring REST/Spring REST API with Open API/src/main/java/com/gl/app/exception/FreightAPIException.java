package com.gl.app.exception;

import org.springframework.http.HttpStatus;

public class FreightAPIException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public FreightAPIException(HttpStatus httpStatus,String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
