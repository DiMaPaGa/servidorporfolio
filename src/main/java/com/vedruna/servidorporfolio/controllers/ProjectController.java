package com.vedruna.servidorporfolio.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.services.ProjectServiceI;
import com.vedruna.servidorporfolio.services.StatusServiceI;
import com.vedruna.servidorporfolio.services.TechnologyServiceI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@Tag(name = "Project", description = "Endpoints for managing operations related to projects")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    
    private final ProjectServiceI projectService;
    private final StatusServiceI statusService;
    private final TechnologyServiceI techService;
    
    @Operation(summary = "Get all projects with pagination", description = "Returns a paginated list of all projects")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Projects fetched successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", 
            content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<ResponseDTO<Page<ProjectDTO>>> getAllProjects(Pageable pageable) {
        Page<ProjectDTO> projects = projectService.getAllProjects(pageable);
        ResponseDTO<Page<ProjectDTO>> response = new ResponseDTO<>("Projects fetched successfully", projects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "Search projects by name keyword", description = "Finds projects that contain a specific keyword in their name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Projects found", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "No projects found with the given keyword", 
            content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{word}")
    public ResponseEntity<ResponseDTO<Page<ProjectDTO>>> getProjectsByNameContaining(@Parameter(description = "Keyword to search for in project names") @PathVariable String word, Pageable pageable) {
        Page<ProjectDTO> projects = projectService.getProjectsByNameContaining(word, pageable);
        ResponseDTO<Page<ProjectDTO>> response = new ResponseDTO<>("Projects found", projects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    @Operation(summary = "Create a new project", description = "Adds a new project to the portfolio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Project created successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid project data", 
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<?>> createProject(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
                
            // Mapear los errores de los campos
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            fieldError -> fieldError.getField(),
                            fieldError -> fieldError.getDefaultMessage()
                    ));
    
            // Revisar errores globales, que son los de la validación personalizada
            bindingResult.getGlobalErrors().forEach(globalError -> {
                System.out.println("Global Error: " + globalError.getDefaultMessage());
                // Si hay un error global (por ejemplo, la validación personalizada de fechas)
                errors.put("global", globalError.getDefaultMessage());
            });
    
            // Responder con los errores de validación
            ResponseDTO<Map<String, String>> response = new ResponseDTO<>("Validation error occurred", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    
        // Si no hay errores de validación, guardamos el proyecto
        ProjectDTO savedProject = projectService.saveProject(projectDTO);
        ResponseDTO<ProjectDTO> response = new ResponseDTO<>("Project created successfully", savedProject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "Update an existing project", description = "Modifies an existing project by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project updated successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Project not found", 
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Invalid project data", 
            content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<ProjectDTO>> updateProject(@Parameter(description = "ID of the project to update") @PathVariable Integer id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        ResponseDTO<ProjectDTO> response = new ResponseDTO<>("Project updated successfully", updatedProject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a project", description = "Removes a project by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project deleted successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Project not found", 
            content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteProject(@Parameter(description = "ID of the project to delete") @PathVariable Integer id) {
        // Llamada al servicio para eliminar el proyecto
        Optional<Project> project = projectService.deleteProject(id);
        
        if (project.isPresent()) {
            // Si el proyecto fue encontrado y eliminado, devuelve un mensaje con el proyecto eliminado
            ResponseDTO<String> response = new ResponseDTO<>(
                "Project deleted successfully", 
                "Project with ID " + id + " was deleted."
            );
            return new ResponseEntity<>(response, HttpStatus.OK);  // Devuelve 200 OK con el mensaje
        } else {
            // Si el proyecto no se encuentra, devuelve un error 404
            ResponseDTO<String> response = new ResponseDTO<>(
                "Project not found", 
                "Project with ID " + id + " does not exist."
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);  // Devuelve 404 NOT FOUND
        }
    }

    @Operation(summary = "Move project to Testing status", description = "Changes a project's status to Testing")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project moved to Testing", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Project not found", 
            content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/{id}/totesting")
    public ResponseEntity<ResponseDTO<String>> moveProjectToTesting(@Parameter(description = "ID of the project to change to Testing status") @PathVariable Integer id) {
        statusService.changeProjectStatus(id, "Testing");
        ResponseDTO<String> response = new ResponseDTO<>("Project moved to Testing", "Project with ID " + id + " is now in Testing.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
    @Operation(summary = "Move project to Production status", description = "Changes a project's status to In Production")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project moved to Testing", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Project not found", 
            content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/{id}/toprod")
    public ResponseEntity<ResponseDTO<String>> moveProjectToProduction(@Parameter(description = "ID of the project to change to Production status") @PathVariable Integer id) {
        statusService.changeProjectStatus(id, "In Production");
        ResponseDTO<String> response = new ResponseDTO<>("Project moved to  In Production", "Project with ID " + id + " is now in Production.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add a technology to a project", description = "Associates a technology with a specific project")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Technology added to project", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Project or Technology not found", 
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/{projectId}/technologies/{techId}")
    public ResponseEntity<ResponseDTO<String>> addTechnologyToProject(@Parameter(description = "ID of the project") @PathVariable Integer projectId,
    @Parameter(description = "ID of the technology to add") @PathVariable Integer techId) {
        techService.addTechnologyToProject(projectId, techId);
        ResponseDTO<String> response = new ResponseDTO<>("Technology added to project", "Technology with ID " + techId + " added to project with ID " + projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get projects by technology", description = "Fetches projects that use a specific technology, with pagination")
    @GetMapping("/tec/{tech}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Projects retrieved successfully", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Technology not found or no projects with the specified technology", 
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Invalid parameters for pagination", 
            content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ResponseDTO<Page<ProjectDTO>>> getProjectsByTechnology(@Parameter(description = "Name of the technology to search projects by") @PathVariable String tech, Pageable pageable) {

        // Llama al servicio para obtener proyectos por tecnología con paginación
        Page<ProjectDTO> projects = projectService.findProjectsByTechnology(tech, pageable);

        // Crear la respuesta con la información paginada
        ResponseDTO<Page<ProjectDTO>> response = new ResponseDTO<>("Projects retrieved successfully", projects);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}