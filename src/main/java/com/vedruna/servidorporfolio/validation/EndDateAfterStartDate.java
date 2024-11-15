package com.vedruna.servidorporfolio.validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EndDateAfterStartDateValidator.class)
public @interface EndDateAfterStartDate {

    String message() default "End date must be equal to or after the start date";
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
