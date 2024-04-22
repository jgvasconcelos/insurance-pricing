package com.jgvasconcelos.insurancebudget.application.web.validator.implementation;

import com.jgvasconcelos.insurancebudget.application.web.validator.NullOrNotBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return field == null || !field.isBlank();
    }
}
