package com.vedruna.servidorporfolio.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.servidorporfolio.dto.DeveloperDTO;
import com.vedruna.servidorporfolio.dto.DeveloperRequestDTO;
import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.services.DeveloperServiceI;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/v1/developers")
@AllArgsConstructor
public class DeveloperController {

    
    private final DeveloperServiceI developerService;

    // Crear un nuevo desarrollador
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> createDeveloper(@Valid @RequestBody DeveloperDTO developerDTO) {
        developerService.saveDeveloper(developerDTO);
        ResponseDTO<String> response = new ResponseDTO<>("Developer created successfully", "Developer with ID " + developerDTO.getDevId() + " created.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Eliminar un desarrollador por ID
    @DeleteMapping("/{devId}")
    public ResponseEntity<ResponseDTO<String>> deleteDeveloper(@PathVariable Integer devId) {
        developerService.deleteDeveloperById(devId);
        ResponseDTO<String> response = new ResponseDTO<>("Developer deleted successfully", "Developer with ID " + devId + " was deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Asociar un desarrollador a un proyecto
    @PostMapping("/worked")
    public ResponseEntity<ResponseDTO<String>> addDeveloperToProject(@RequestBody DeveloperRequestDTO developerRequestDTO) {
        developerService.addDeveloperToProject(developerRequestDTO.getDevId(), developerRequestDTO.getProjectId());
        ResponseDTO<String> response = new ResponseDTO<>("Developer associated with project", "Developer with ID " + developerRequestDTO.getDevId() + " worked on project with ID " + developerRequestDTO.getProjectId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    
    
}
