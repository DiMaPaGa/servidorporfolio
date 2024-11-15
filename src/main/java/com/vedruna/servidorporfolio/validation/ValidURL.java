package com.vedruna.servidorporfolio.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = URLValidator.class) // Referencia a tu validador personalizado
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURL {
    String message() default "Invalid URL format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

