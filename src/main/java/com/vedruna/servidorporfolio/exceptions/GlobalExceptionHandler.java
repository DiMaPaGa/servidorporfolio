package com.vedruna.servidorporfolio.exceptions;


import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vedruna.servidorporfolio.dto.ResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
 /**
     * Maneja la excepción {@link DeveloperNotFoundException} cuando no se encuentra un desarrollador.
     *
     * @param ex La excepción {@link DeveloperNotFoundException} que contiene el mensaje de error.
     * @return Una respuesta con el código de estado HTTP 404 (Not Found) y un mensaje detallado.
     */
 @ExceptionHandler(DeveloperNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleDeveloperNotFound(DeveloperNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Developer not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

   /**
     * Maneja la excepción {@link ProjectNotFoundException} cuando no se encuentra un proyecto.
     *
     * @param ex La excepción {@link ProjectNotFoundException} que contiene el mensaje de error.
     * @return Una respuesta con el código de estado HTTP 404 (Not Found) y un mensaje detallado.
     */
 @ExceptionHandler(ProjectNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleProjectNotFound(ProjectNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Project not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

   /**
     * Maneja la excepción {@link StatusNotFoundException} cuando no se encuentra un estado.
     *
     * @param ex La excepción {@link StatusNotFoundException} que contiene el mensaje de error.
     * @return Una respuesta con el código de estado HTTP 404 (Not Found) y un mensaje detallado.
     */
 @ExceptionHandler(StatusNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleStatusNotFound(StatusNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Status not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

   /**
     * Maneja la excepción {@link TechnologyNotFoundException} cuando no se encuentra una tecnología.
     *
     * @param ex La excepción {@link TechnologyNotFoundException} que contiene el mensaje de error.
     * @return Una respuesta con el código de estado HTTP 404 (Not Found) y un mensaje detallado.
     */
 @ExceptionHandler(TechnologyNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleTechnologyNotFound(TechnologyNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Technology not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }


  /**
     * Maneja la excepción {@link TechnologyAlreadyExistsException} cuando ya existe una tecnología.
     *
     * @param ex La excepción {@link TechnologyAlreadyExistsException} que contiene el mensaje de error.
     * @return Una respuesta con el código de estado HTTP 409 (Conflict) y un mensaje detallado.
     */
@ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<String>> handleTechnologyAlreadyExists(TechnologyAlreadyExistsException ex) {
        ResponseDTO<String> response = new ResponseDTO<>(
            "Technology already exists", 
            ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);  // Código 409 Conflict
    }

  /**
     * Maneja la excepción {@link EntityNotFoundException} para entidades no encontradas.
     *
     * @param ex La excepción {@link EntityNotFoundException} que contiene el mensaje de error.
     * @return Una respuesta con el código de estado HTTP 404 (Not Found) y un mensaje detallado.
     */
 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleEntityNotFound(EntityNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Entity not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 /**
     * Maneja los errores de integridad de base de datos ({@link DataIntegrityViolationException}).
     *
     * @param ex La excepción {@link DataIntegrityViolationException} que contiene los detalles del error.
     * @return Una respuesta con el código de estado HTTP 400 (Bad Request) y un mensaje detallado sobre la violación de la restricción de integridad.
     */
 @ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<ResponseDTO<String>> handleDatabaseErrors(DataIntegrityViolationException ex) {
    ResponseDTO<String> response = new ResponseDTO<>(
        "Database error occurred", 
        "Integrity constraint violation: "+ ex.getMostSpecificCause().getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    
    /**
     * Maneja las excepciones de violación de restricciones de validación, como las anotaciones {@link jakarta.validation} (por ejemplo, @NotNull, @Size).
     *
     * @param ex La excepción {@link ConstraintViolationException} que contiene las violaciones de las restricciones.
     * @return Una respuesta con el código de estado HTTP 400 (Bad Request) y un mensaje que describe las violaciones de restricciones.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDTO<String>> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        // Extraemos los mensajes de error de la excepción de violación de restricciones
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));

        ResponseDTO<String> response = new ResponseDTO<>(
                "Constraint violation occurred",
                errorMessage
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    /**
     * Maneja los errores de validación de parámetros en los controladores que usan {@link jakarta.validation.Valid}.
     *
     * @param ex La excepción {@link MethodArgumentNotValidException} que contiene los errores de validación de parámetros.
     * @return Una respuesta con el código de estado HTTP 400 (Bad Request) y un mapa de los errores de validación.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage()
                ));

        ResponseDTO<Map<String, String>> response = new ResponseDTO<>(
                "Validation error occurred",
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

     /**
     * Maneja las excepciones de formato incorrecto de los mensajes HTTP, como una carga útil de solicitud no legible.
     *
     * @param ex La excepción {@link HttpMessageNotReadableException} que indica que el formato del mensaje no es legible.
     * @return Una respuesta con el código de estado HTTP 400 (Bad Request) y un mensaje indicando el error de formato.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ResponseDTO<String> response = new ResponseDTO<>(
            "Invalid request payload",
            "The provided JSON or payload format is incorrect"
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


     /**
     * Maneja cualquier excepción no controlada por los manejadores específicos.
     *
     * @param ex La excepción {@link Exception} que ocurre en situaciones inesperadas.
     * @return Una respuesta con el código de estado HTTP 500 (Internal Server Error) y un mensaje genérico sobre el error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGeneralException(Exception ex) {
        ResponseDTO<String> response = new ResponseDTO<>(
            "Unexpected error occurred", 
            ex.getMessage() // Mensaje genérico para errores no controlados
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
