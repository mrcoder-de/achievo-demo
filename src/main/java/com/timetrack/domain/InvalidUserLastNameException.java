package com.timetrack.domain;

public class InvalidUserLastNameException extends RuntimeException {
    public InvalidUserLastNameException(String message) {
        super(message);
    }
}