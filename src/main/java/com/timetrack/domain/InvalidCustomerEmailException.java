package com.timetrack.domain;

public class InvalidCustomerEmailException extends RuntimeException {
    public InvalidCustomerEmailException(String message) {
        super(message);
    }
}