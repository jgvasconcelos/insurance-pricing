package com.jgvasconcelos.insurancebudget.resources.repository.driver.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.ResourceAlreadyExistsException;

public class DriverAlreadyExistsException extends ResourceAlreadyExistsException {
    public DriverAlreadyExistsException(String message) {
        super(message);
    }
}
