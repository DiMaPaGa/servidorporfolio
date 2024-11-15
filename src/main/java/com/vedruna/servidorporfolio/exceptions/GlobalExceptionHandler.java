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
    
 // Maneja DeveloperNotFoundException
 @ExceptionHandler(DeveloperNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleDeveloperNotFound(DeveloperNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Developer not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 // Maneja ProjectNotFoundException
 @ExceptionHandler(ProjectNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleProjectNotFound(ProjectNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Project not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 // Maneja StatusNotFoundException
 @ExceptionHandler(StatusNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleStatusNotFound(StatusNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Status not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 // Maneja TechnologyNotFoundException
 @ExceptionHandler(TechnologyNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleTechnologyNotFound(TechnologyNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Technology not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

@ExceptionHandler(TechnologyAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO<String>> handleTechnologyAlreadyExists(TechnologyAlreadyExistsException ex) {
        ResponseDTO<String> response = new ResponseDTO<>(
            "Technology already exists", 
            ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);  // Código 409 Conflict
    }

 // Maneja EntityNotFoundException generico
 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleEntityNotFound(EntityNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Entity not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<ResponseDTO<String>> handleDatabaseErrors(DataIntegrityViolationException ex) {
    ResponseDTO<String> response = new ResponseDTO<>(
        "Database error occurred", 
        "Integrity constraint violation: "+ ex.getMostSpecificCause().getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    
    // Maneja las violaciones de restricciones de JPA (por ejemplo, @NotNull, @Size, etc.)
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



    // Maneja los errores de validación para parámetros del controlador (usando @Valid)
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDTO<String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ResponseDTO<String> response = new ResponseDTO<>(
            "Invalid request payload",
            "The provided JSON or payload format is incorrect"
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    // Manejador genérico para excepciones no controladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGeneralException(Exception ex) {
        ResponseDTO<String> response = new ResponseDTO<>(
            "Unexpected error occurred", 
            ex.getMessage() // Mensaje genérico para errores no controlados
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
