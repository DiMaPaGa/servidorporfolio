package com.vedruna.servidorporfolio.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.vedruna.servidorporfolio.dto.ProjectDTO;

/**
 * Validador personalizado para la anotación {@link EndDateAfterStartDate}.
 * Se utiliza para asegurarse de que la fecha de fin de un proyecto no sea
 * anterior a la fecha de inicio.
 */
public class EndDateAfterStartDateValidator implements ConstraintValidator<EndDateAfterStartDate, ProjectDTO> {

    /**
     * Valida si un {@link ProjectDTO} cumple con la regla de que la fecha de fin
     * debe ser igual o posterior a la fecha de inicio.
     *
     * @param projectDTO el objeto a validar.
     * @param context el contexto de la validación, utilizado para construir mensajes de error.
     * @return {@code true} si el DTO es válido; {@code false} de lo contrario.
     */
    @Override
    public boolean isValid(ProjectDTO projectDTO, ConstraintValidatorContext context) {
        if (projectDTO == null) {
            return true; // Si el DTO es nulo, lo consideramos válido
        }

        // Validar que la fecha de fin no sea anterior a la de inicio
        if (projectDTO.getStartDate() != null && projectDTO.getEndDate() != null) {
                if (projectDTO.getEndDate().isBefore(projectDTO.getStartDate())) {
                    // Si la fecha de fin es anterior a la fecha de inicio, marcar como inválido
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("End date must be equal to or after the start date")
                        .addConstraintViolation();
                    return false;  // La validación falla
                }
        }

        return true;  // Si no hay errores, es válido
    }

}

