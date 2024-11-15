package com.vedruna.servidorporfolio.validation;

import java.net.MalformedURLException;
import java.net.URL;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class URLValidator implements ConstraintValidator<ValidURL, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Permite valores nulos o vacíos, dependiendo de la especificación
        }

        try {
            new URL(value); // Valida si es una URL válida
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
