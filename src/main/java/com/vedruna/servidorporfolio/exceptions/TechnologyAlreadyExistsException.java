package com.vedruna.servidorporfolio.exceptions;

/**
 * Excepción personalizada que se lanza cuando se intenta agregar una tecnología que ya existe
 * en el sistema. Esta excepción es una subclase de {@link RuntimeException} y se utiliza para
 * indicar que la operación no puede completarse debido a la duplicación de una tecnología.
 * 
 * @author [Diana Mª Pascual García]
 * @version 1.0
 */

public  class TechnologyAlreadyExistsException extends RuntimeException{

    /**
     * Constructor para crear una nueva instancia de {@link TechnologyAlreadyExistsException}.
     * 
     * @param message El mensaje de error que describe la razón por la cual la tecnología ya existe.
     */
    public TechnologyAlreadyExistsException(String message) {
        super(message);
    }
    
}
