package com.timetrack.domain;

public class CostCenterNotFoundException extends RuntimeException {
    public CostCenterNotFoundException(String message) {
        super(message);
    }
}