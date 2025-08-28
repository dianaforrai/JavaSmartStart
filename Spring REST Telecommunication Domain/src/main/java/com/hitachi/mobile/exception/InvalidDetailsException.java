package com.hitachi.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDetailsException extends RuntimeException {
    public InvalidDetailsException(String message) {
        super(message);
    }
}