package com.vedruna.servidorporfolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vedruna.servidorporfolio.dto.ResponseDTO;

import jakarta.persistence.EntityNotFoundException;

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

 // Maneja EntityNotFoundException generico
 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity<ResponseDTO<String>> handleEntityNotFound(EntityNotFoundException ex) {
     ResponseDTO<String> response = new ResponseDTO<>(
         "Entity not found", 
         ex.getMessage() // El mensaje personalizado de la excepción
     );
     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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
