package com.pranhirefy.employee.exception;

public class EmptyRequestBodyException extends RuntimeException {
    public EmptyRequestBodyException(String message) {
        super(message);
    }
}
