package com.vedruna.servidorporfolio.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.dto.TechnologyDTO;
import com.vedruna.servidorporfolio.dto.TechnologyRequestDTO;
import com.vedruna.servidorporfolio.services.TechnologyServiceI;

import lombok.AllArgsConstructor;

@Tag(name = "Tecnology", description = "Endpoints for managing technologies in this portfolio")
@RestController
@RequestMapping("/api/v1/technologies")
@AllArgsConstructor
public class TechnologyController {

    private final TechnologyServiceI technologyService;

    @Operation(summary = "Add technology to a project", description = "Associates an existing technology with a specified project")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Technology added to project", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Project or Technology not found", 
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/used")
    public ResponseEntity<ResponseDTO<String>> addTechnologyToProject(@Valid @RequestBody TechnologyRequestDTO techrRequestDTO){
        technologyService.addTechnologyToProject(techrRequestDTO.getProjectId(), techrRequestDTO.getTechId());
    ResponseDTO<String> response = new ResponseDTO<>("Technology added to project", 
            "Technology with ID " + techrRequestDTO.getTechId() + " added to project with ID " + techrRequestDTO.getProjectId());
    return new ResponseEntity<>(response, HttpStatus.OK);
}

    @Operation(summary = "Create a new technology", description = "Creates a new technology in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Technology created successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid technology data", 
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> createTechnology(@RequestBody TechnologyDTO technologyDTO) {
        technologyService.createTechnology(technologyDTO);
        ResponseDTO<String> response = new ResponseDTO<>("Technology created successfully", "Technology with name " + technologyDTO.getTechName() + " created.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a technology by ID", description = "Deletes a technology from the system by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Technology deleted successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Technology not found", 
            content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{techId}")
    public ResponseEntity<ResponseDTO<String>> deleteTechnology(@Parameter(description = "ID of the technology to delete") @PathVariable Integer techId) {
        technologyService.deleteTechnology(techId);
        ResponseDTO<String> response = new ResponseDTO<>("Technology deleted successfully", "Technology with ID " + techId + " deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}