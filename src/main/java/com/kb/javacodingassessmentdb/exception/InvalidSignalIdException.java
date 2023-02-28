package com.kb.javacodingassessmentdb.exception;

public class InvalidSignalIdException extends RuntimeException {
    private static final long serialVersionUID = 1057160888997067679L;

    public InvalidSignalIdException(String message) {
        super(message);
    }
}
