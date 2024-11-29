package com.timetrack.domain;

public class InvalidUserFirstNameException extends RuntimeException {
    public InvalidUserFirstNameException(String message) {
        super(message);
    }
}