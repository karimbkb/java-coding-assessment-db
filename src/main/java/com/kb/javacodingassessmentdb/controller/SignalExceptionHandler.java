package com.kb.javacodingassessmentdb.controller;

import com.kb.javacodingassessmentdb.exception.ExceptionInfo;
import com.kb.javacodingassessmentdb.exception.InvalidSignalIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class SignalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionInfo> handleAllExceptions(Exception e, WebRequest request) {
        ExceptionInfo exceptionResponse = new ExceptionInfo(new Date(), e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidSignalIdException.class)
    public final ResponseEntity<ExceptionInfo> handleUserNotFoundException(InvalidSignalIdException e, WebRequest request) {
        ExceptionInfo exceptionResponse = new ExceptionInfo(new Date(), e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
