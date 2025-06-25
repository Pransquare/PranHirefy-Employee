package com.pranhirefy.employee.exception;

import java.util.Map;

public class CombinedValidationException extends RuntimeException {
    private final Map<String, String> errors;

    public CombinedValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
