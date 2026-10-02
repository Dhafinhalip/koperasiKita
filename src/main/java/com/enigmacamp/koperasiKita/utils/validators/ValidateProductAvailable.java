package com.enigmacamp.koperasiKita.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidateProductAvailable implements ConstraintValidator<ValidProductAvailable, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }
}
