package com.vedruna.servidorporfolio.validation;

import java.net.MalformedURLException;
import java.net.URL;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validador personalizado para la anotación {@link ValidURL}.
 * Este validador verifica si una cadena de texto es una URL válida.
 * 
 * <p>Permite valores nulos o vacíos, considerando que en esos casos no es necesario
 * realizar validación (dependiendo de la especificación de la anotación).</p>
 */
public class URLValidator implements ConstraintValidator<ValidURL, String> {

    /**
     * Valida si el valor proporcionado es una URL válida.
     * 
     * @param value el valor de la cadena que se va a validar.
     * @param context el contexto de la validación, utilizado para agregar mensajes de error.
     * @return {@code true} si el valor es una URL válida o si es {@code null} o vacío; {@code false} si la URL es inválida.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Permite valores nulos o vacíos, dependiendo de la especificación
        }

        try {
            new URL(value); // Valida si es una URL válida
            return true;
        } catch (MalformedURLException e) {
            return false; // Si ocurre una excepción, es una URL mal formada
        }
    }
}
