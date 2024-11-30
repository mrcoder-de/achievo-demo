package com.timetrack.domain;

public class ActivityValidationException extends RuntimeException {
    public ActivityValidationException(String message) {
        super(message);
    }
}