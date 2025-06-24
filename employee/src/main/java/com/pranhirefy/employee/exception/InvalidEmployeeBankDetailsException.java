package com.pranhirefy.employee.exception;

import java.util.Map;

public class InvalidEmployeeBankDetailsException  extends RuntimeException {
    private final Map<String, String> errors;

    public InvalidEmployeeBankDetailsException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}