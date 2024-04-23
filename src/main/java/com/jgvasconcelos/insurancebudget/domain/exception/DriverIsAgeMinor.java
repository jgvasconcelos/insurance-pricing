package com.jgvasconcelos.insurancebudget.domain.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.RuleViolationException;

public class DriverIsAgeMinor extends RuleViolationException {
    public DriverIsAgeMinor(String message) {
        super(message);
    }
}
