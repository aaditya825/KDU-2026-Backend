package com.logisticshub.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleGeneralValidationException(MethodArgumentNotValidException exception)
    {
        ErrorResponse error = new ErrorResponse();

        error.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectBookStatusException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectBookStatusException(IncorrectBookStatusException exception)
    {
        ErrorResponse error = new ErrorResponse();

        error.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
