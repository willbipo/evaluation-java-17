package com.williams.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomExceptionResponse> handleNotFoundException(ModelNotFoundException ex, WebRequest req){
        return new ResponseEntity<>(new CustomExceptionResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionResponse> handleCustomException(Exception ex, WebRequest req){
        return new ResponseEntity<>(new CustomExceptionResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new CustomExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String exception = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField()+ ": "+error.getDefaultMessage()).collect(Collectors.joining());
        return new ResponseEntity<>(new CustomExceptionResponse(LocalDateTime.now(),exception , request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }
}
