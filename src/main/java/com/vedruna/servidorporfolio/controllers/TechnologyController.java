package com.vedruna.servidorporfolio.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.dto.TechnologyDTO;
import com.vedruna.servidorporfolio.dto.TechnologyRequestDTO;
import com.vedruna.servidorporfolio.services.TechnologyServiceI;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/technologies")
@AllArgsConstructor
public class TechnologyController {

    private final TechnologyServiceI technologyService;

    // Endpoint para agregar una tecnología a un proyecto
    @PostMapping("/used")
    public ResponseEntity<ResponseDTO<String>> addTechnologyToProject(@RequestBody TechnologyRequestDTO techrRequestDTO){
        technologyService.addTechnologyToProject(techrRequestDTO.getProjectId(), techrRequestDTO.getTechId());
    ResponseDTO<String> response = new ResponseDTO<>("Technology added to project", 
            "Technology with ID " + techrRequestDTO.getTechId() + " added to project with ID " + techrRequestDTO.getProjectId());
    return new ResponseEntity<>(response, HttpStatus.OK);
}

    // Endpoint para crear una nueva tecnología
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> createTechnology(@RequestBody TechnologyDTO technologyDTO) {
        technologyService.createTechnology(technologyDTO);
        ResponseDTO<String> response = new ResponseDTO<>("Technology created successfully", "Technology with name " + technologyDTO.getTechName() + " created.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint para eliminar una tecnología por ID
    @DeleteMapping("/{techId}")
    public ResponseEntity<ResponseDTO<String>> deleteTechnology(@PathVariable Integer techId) {
        technologyService.deleteTechnology(techId);
        ResponseDTO<String> response = new ResponseDTO<>("Technology deleted successfully", "Technology with ID " + techId + " deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}