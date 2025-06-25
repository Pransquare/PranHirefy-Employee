package com.pranhirefy.employee.exception;

public class CreatedByRequiredException extends RuntimeException {
    public CreatedByRequiredException(String msg) {
        super(msg);
    }
}