package ru.otus.spring.rest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BookControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String FORMAT_AUTHORS_ERROR = "Error format of authors";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest req) {
        return handleExceptionInternal(ex, FORMAT_AUTHORS_ERROR, new HttpHeaders(), HttpStatus.BAD_REQUEST, req);
    }
}