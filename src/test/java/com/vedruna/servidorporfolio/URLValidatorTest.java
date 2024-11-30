package com.vedruna.servidorporfolio;
import com.vedruna.servidorporfolio.validation.ValidURL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class URLValidatorTest {

    private final Validator validator;

    /**
     * Constructor que inicializa el validador de Jakarta Validation.
     */
    URLValidatorTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     /**
     * Prueba de validación de URLs válidas.
     * Verifica que una URL correctamente formada sea validada sin errores.
     */
    @Test
    void testValidURLs() {
        // Ejemplo con una URL válida
        TestEntity entity = new TestEntity("https://www.example.com");
        var violations = validator.validate(entity);
        assertTrue(violations.isEmpty());
    }

    /**
     * Prueba de validación de URLs inválidas.
     * Verifica que una URL mal formada genere una violación de validación.
     */
    @Test
    void testInvalidURLs() {
        // Ejemplo con una URL inválida
        TestEntity entity = new TestEntity("invalid-url");
        var violations = validator.validate(entity);
        assertFalse(violations.isEmpty());
    }

    /**
     * Clase interna de prueba utilizada para validar URLs.
     */
    static class TestEntity {
        @ValidURL
        private final String url;

        TestEntity(String url) {
            this.url = url;
        }
    }
}
