package com.jgvasconcelos.insurancebudget.resources.repository.driver.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.ResourceNotFoundException;

public class DriverNotFoundException extends ResourceNotFoundException {
    public DriverNotFoundException(String message) {
        super(message);
    }
}
