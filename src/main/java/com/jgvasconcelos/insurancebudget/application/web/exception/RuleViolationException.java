package com.jgvasconcelos.insurancebudget.application.web.exception;

public class RuleViolationException extends Exception{
    public RuleViolationException(String message) {
        super(message);
    }
}
