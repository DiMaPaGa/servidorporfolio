package com.vedruna.servidorporfolio.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * La anotación {@code ValidURL} valida que un campo contenga una URL válida.
 * Utiliza el validador {@link URLValidator} para comprobar el formato de la URL.
 * 
 * @see URLValidator
 */
@Constraint(validatedBy = URLValidator.class) // Referencia al validador personalizado
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURL {

     /**
     * Mensaje de error cuando el formato de la URL no es válido.
     * 
     * @return El mensaje de error
     */
    String message() default "Invalid URL format";

    /**
     * Grupos de validación a los que se asigna esta anotación.
     * 
     * @return Los grupos de validación
     */
    Class<?>[] groups() default {};

     /**
     * Carga útil asociada a la validación.
     * 
     * @return La carga útil
     */
    Class<? extends Payload>[] payload() default {};
}

