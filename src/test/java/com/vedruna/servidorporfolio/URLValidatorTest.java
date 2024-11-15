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

    URLValidatorTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidURLs() {
        // Ejemplo con una URL válida
        TestEntity entity = new TestEntity("https://www.example.com");
        var violations = validator.validate(entity);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidURLs() {
        // Ejemplo con una URL inválida
        TestEntity entity = new TestEntity("invalid-url");
        var violations = validator.validate(entity);
        assertFalse(violations.isEmpty());
    }

    static class TestEntity {
        @ValidURL
        private final String url;

        TestEntity(String url) {
            this.url = url;
        }
    }
}
