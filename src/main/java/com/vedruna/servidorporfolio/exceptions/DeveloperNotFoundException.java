package com.vedruna.servidorporfolio.exceptions;

/**
 * Excepción que se lanza cuando no se encuentra un desarrollador en el sistema.
 * 
 */
public class DeveloperNotFoundException extends RuntimeException{

     /**
     * Construye una nueva DeveloperNotFoundException con un mensaje detallado.
     *
     * @param message el mensaje detallado que explica la razón de la excepción
     */
    public DeveloperNotFoundException(String message) {
        super(message);
    }
}
