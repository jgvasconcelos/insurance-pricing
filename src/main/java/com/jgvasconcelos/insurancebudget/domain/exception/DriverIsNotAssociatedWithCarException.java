package com.jgvasconcelos.insurancebudget.domain.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.RuleViolationException;

public class DriverIsNotAssociatedWithCarException extends RuleViolationException {
    public DriverIsNotAssociatedWithCarException(String message) {
        super(message);
    }
}
