package com.enigmacamp.koperasiKita.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidateProductAvailable.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProductAvailable {
    String message() default "Product Available note is not valid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
