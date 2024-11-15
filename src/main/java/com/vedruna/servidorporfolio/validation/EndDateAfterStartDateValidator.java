package com.vedruna.servidorporfolio.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.vedruna.servidorporfolio.dto.ProjectDTO;

public class EndDateAfterStartDateValidator implements ConstraintValidator<EndDateAfterStartDate, ProjectDTO> {

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

