package com.jgvasconcelos.insurancebudget.resources.repository.accident.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.ResourceNotFoundException;

public class AccidentNotFoundException extends ResourceNotFoundException {
    public AccidentNotFoundException(String message) {
        super(message);
    }
}
