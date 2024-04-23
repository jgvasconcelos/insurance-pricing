package com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.exception;

import com.jgvasconcelos.insurancebudget.application.web.exception.ResourceNotFoundException;

public class InsuranceBudgetNotFoundException extends ResourceNotFoundException {
    public InsuranceBudgetNotFoundException(String message) {
        super(message);
    }
}
