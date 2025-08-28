package com.hitachi.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DetailsDoesNotExistException extends RuntimeException {
    public DetailsDoesNotExistException(String message) {
        super(message);
    }
}