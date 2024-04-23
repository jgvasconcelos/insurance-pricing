package com.jgvasconcelos.insurancebudget.domain.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.RuleViolationException;

public class DriverIsNotMainDriverOfCarException extends RuleViolationException {
    public DriverIsNotMainDriverOfCarException(String message) {
        super(message);
    }
}
