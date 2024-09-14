package com.luciano.springboot.app.crud.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserConflictException extends Exception {
    public UserConflictException(String message) {
        super(message);
    }
}
