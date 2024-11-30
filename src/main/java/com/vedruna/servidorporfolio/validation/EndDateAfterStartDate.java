package com.vedruna.servidorporfolio.validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Anotación personalizada para validar que la fecha de finalización 
 * de un objeto sea igual o posterior a la fecha de inicio.
 * 
 * Se utiliza principalmente en clases de modelo o DTO donde 
 * se requiere esta validación de rango de fechas.
 * 
 * @author [Diana Mª Pascual García]
 */
@Target({ElementType.TYPE}) // Aplica a clases, interfaces o enums.
@Retention(RetentionPolicy.RUNTIME) // Se mantiene en tiempo de ejecución.
@Constraint(validatedBy = EndDateAfterStartDateValidator.class) // Clase validadora asociada.
public @interface EndDateAfterStartDate {

    /**
     * Mensaje de error que se mostrará si la validación falla.
     *
     * @return El mensaje predeterminado.
     */
    String message() default "End date must be equal to or after the start date";
    
    /**
     * Grupos de validación a los que pertenece esta restricción.
     *
     * @return Array de clases de grupo.
     */
    Class<?>[] groups() default {};

    /**
     * Información adicional sobre la carga útil de la restricción.
     *
     * @return Array de clases de carga útil.
     */
    Class<? extends Payload>[] payload() default {};
    
}
