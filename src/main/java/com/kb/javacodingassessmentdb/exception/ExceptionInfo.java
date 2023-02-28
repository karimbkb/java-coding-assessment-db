package com.kb.javacodingassessmentdb.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ExceptionInfo {
    private final Date timestamp;
    private final String message;
    private final String details;
}
