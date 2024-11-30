package com.timetrack.domain;

public class CostCenterNameAlreadyExistsException extends RuntimeException {
    public CostCenterNameAlreadyExistsException(String message) {
        super(message);
    }
}