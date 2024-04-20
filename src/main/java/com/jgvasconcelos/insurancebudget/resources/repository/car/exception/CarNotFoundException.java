package com.jgvasconcelos.insurancebudget.resources.repository.car.exception;

import com.jgvasconcelos.insurancebudget.application.exception.ResourceNotFoundException;

public class CarNotFoundException extends ResourceNotFoundException {
    public CarNotFoundException(String message) {
        super(message);
    }
}
