package com.jgvasconcelos.insurancebudget.resources.repository.cardriver.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.ResourceNotFoundException;

public class CarDriverNotFoundException extends ResourceNotFoundException {
    public CarDriverNotFoundException(String message) {
        super(message);
    }
}
