package com.timetrack.domain;

public class CostCenterValidationException extends RuntimeException {
    public CostCenterValidationException(String message) {
        super(message);
    }
}