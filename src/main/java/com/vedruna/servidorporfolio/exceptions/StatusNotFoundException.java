package com.vedruna.servidorporfolio.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un estado en el sistema.
 * Esta excepción es una subclase de {@link RuntimeException} y se utiliza para indicar que
 * un estado no ha sido encontrado durante la ejecución de alguna operación.
 * 
 * @author [Diana Mª Pascual García]
 * @version 1.0
 */
public class StatusNotFoundException extends RuntimeException {

    /**
     * Constructor para crear una nueva instancia de {@link StatusNotFoundException}.
     * 
     * @param message El mensaje de error que describe la razón por la cual el estado no fue encontrado.
     */
    public StatusNotFoundException(String message) {
        super(message);
    }
}
