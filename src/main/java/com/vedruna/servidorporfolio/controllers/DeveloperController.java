package com.vedruna.servidorporfolio.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.servidorporfolio.dto.DeveloperDTO;
import com.vedruna.servidorporfolio.dto.DeveloperRequestDTO;
import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.services.DeveloperServiceI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Tag(name = "Developers", description = "Endpoints for managing developers in this portfolio")
@RestController
@RequestMapping("/api/v1/developers")
@AllArgsConstructor
public class DeveloperController {

    
    private final DeveloperServiceI developerService;

    @Operation(
        summary = "Create a new developer",
        description = "Creates a new developer in the system",
        responses = {
            @ApiResponse(responseCode = "201", description = "Developer created successfully",
                        content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors")
        }
    )
    @PostMapping
    public ResponseEntity<ResponseDTO<String>> createDeveloper(
        @Valid @RequestBody @Parameter(description = "Details of the developer to be created") DeveloperDTO developerDTO) {
        developerService.saveDeveloper(developerDTO);
        ResponseDTO<String> response = new ResponseDTO<>("Developer created successfully", "Developer with ID " + developerDTO.getDevId() + " created.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }





    @Operation(
        summary = "Delete a developer by ID",
        description = "Deletes a developer from the system based on the provided developer ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Developer deleted successfully",
                        content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Developer not found")
        }
    )
    @DeleteMapping("/{devId}")
    public ResponseEntity<ResponseDTO<String>> deleteDeveloper(@PathVariable @Parameter(description = "ID of the developer to be deleted") Integer devId) {
        developerService.deleteDeveloperById(devId);
        ResponseDTO<String> response = new ResponseDTO<>("Developer deleted successfully", "Developer with ID " + devId + " was deleted.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(
        summary = "Add a developer to a project",
        description = "Associates a developer with a specific project",
        responses = {
            @ApiResponse(responseCode = "200", description = "Developer associated with project",
                        content = @Content(mediaType = "application/json", 
                                        schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, validation errors"),
            @ApiResponse(responseCode = "404", description = "Developer or project not found")
        }
    )
    @PostMapping("/worked")
    public ResponseEntity<ResponseDTO<String>> addDeveloperToProject(@RequestBody @Parameter(description = "Developer and project IDs to associate") DeveloperRequestDTO developerRequestDTO) {
        developerService.addDeveloperToProject(developerRequestDTO.getDevId(), developerRequestDTO.getProjectId());
        ResponseDTO<String> response = new ResponseDTO<>("Developer associated with project", "Developer with ID " + developerRequestDTO.getDevId() + " worked on project with ID " + developerRequestDTO.getProjectId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    
    
}
