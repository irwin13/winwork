package com.irwin13.winwork.basic.validator;


public enum ValidationStatus {
    SUCCESS("SUCCESS"), ERROR("ERROR");

    private final String status;

    private ValidationStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}