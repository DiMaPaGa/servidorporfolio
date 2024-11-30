package com.vedruna.servidorporfolio.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra una tecnología en el sistema.
 * Esta excepción es una subclase de {@link RuntimeException} y se utiliza para indicar que 
 * la tecnología solicitada no existe en la base de datos o en el contexto de la aplicación.
 * 
 * @author [Diana Mª Pascual García]
 * @version 1.0
 */
public class TechnologyNotFoundException extends RuntimeException {

    /**
     * Constructor para crear una nueva instancia de {@link TechnologyNotFoundException}.
     * 
     * @param message El mensaje de error que describe la razón por la cual la tecnología no fue encontrada.
     */
    public TechnologyNotFoundException(String message) {
        super(message);
    }
}
