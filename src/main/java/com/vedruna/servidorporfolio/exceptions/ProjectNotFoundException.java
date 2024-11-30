package com.vedruna.servidorporfolio.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un proyecto en el sistema.
 * Esta excepción es una subclase de {@link RuntimeException} y se utiliza para indicar que
 * un proyecto no ha sido encontrado durante la ejecución de alguna operación.
 * 
 * @author [Diana Mª Pascual García]
 * @version 1.0
 */

public class ProjectNotFoundException extends RuntimeException {

    /**
     * Constructor para crear una nueva instancia de {@link ProjectNotFoundException}.
     * 
     * @param message El mensaje de error que describe la razón por la cual el proyecto no fue encontrado.
     */
    public ProjectNotFoundException(String message) {
        super(message);
    }
}