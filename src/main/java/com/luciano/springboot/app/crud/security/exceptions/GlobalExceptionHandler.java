package com.luciano.springboot.app.crud.security.exceptions;

import com.luciano.springboot.app.crud.security.models.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserConflictException.class)
    public ResponseEntity<ErrorDto> userExistException(UserConflictException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "User already exists",
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
