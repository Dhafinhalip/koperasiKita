package com.enigmacamp.koperasiKita.utils.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint()
@Target()
@Retention()
public @interface ValidProductAvailable {
    String message() default "Product Available note is not valid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}
