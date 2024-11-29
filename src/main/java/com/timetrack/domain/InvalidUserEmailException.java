package com.timetrack.domain;

public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException(String message) {
        super(message);
    }
}