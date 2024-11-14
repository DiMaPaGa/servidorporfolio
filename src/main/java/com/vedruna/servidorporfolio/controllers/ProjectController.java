package com.vedruna.servidorporfolio.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.dto.ResponseDTO;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.services.ProjectServiceI;
import com.vedruna.servidorporfolio.services.StatusServiceI;
import com.vedruna.servidorporfolio.services.TechnologyServiceI;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    
    private final ProjectServiceI projectService;
    private final StatusServiceI statusService;
    private final TechnologyServiceI techService;
    
    // Obtener todos los proyectos
    @GetMapping
    public ResponseEntity<ResponseDTO<Page<ProjectDTO>>> getAllProjects(Pageable pageable) {
        Page<ProjectDTO> projects = projectService.getAllProjects(pageable);
        ResponseDTO<Page<ProjectDTO>> response = new ResponseDTO<>("Projects fetched successfully", projects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     // Buscar proyectos por palabra clave en su nombre
    @GetMapping("/{word}")
    public ResponseEntity<ResponseDTO<Page<ProjectDTO>>> getProjectsByNameContaining(@PathVariable String word, Pageable pageable) {
        Page<ProjectDTO> projects = projectService.getProjectsByNameContaining(word, pageable);
        ResponseDTO<Page<ProjectDTO>> response = new ResponseDTO<>("Projects found", projects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<ResponseDTO<ProjectDTO>> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        ProjectDTO savedProject = projectService.saveProject(projectDTO);
        ResponseDTO<ProjectDTO> response = new ResponseDTO<>("Project created successfully", savedProject);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Actualizar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<ProjectDTO>> updateProject(@PathVariable Integer id, @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        ResponseDTO<ProjectDTO> response = new ResponseDTO<>("Project updated successfully", updatedProject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteProject(@PathVariable Integer id) {
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

    // Cambiar el estado de un proyecto a "Testing"
    @PatchMapping("/{id}/totesting")
    public ResponseEntity<ResponseDTO<String>> moveProjectToTesting(@PathVariable Integer id) {
        statusService.changeProjectStatus(id, "Testing");
        ResponseDTO<String> response = new ResponseDTO<>("Project moved to Testing", "Project with ID " + id + " is now in Testing.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Cambiar el estado de un proyecto a "Production"
    @PatchMapping("/{id}/toprod")
    public ResponseEntity<ResponseDTO<String>> moveProjectToProduction(@PathVariable Integer id) {
        statusService.changeProjectStatus(id, "In Production");
        ResponseDTO<String> response = new ResponseDTO<>("Project moved to  In Production", "Project with ID " + id + " is now in Production.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Asociar una tecnología a un proyecto
    @PostMapping("/{projectId}/technologies/{techId}")
    public ResponseEntity<ResponseDTO<String>> addTechnologyToProject(@PathVariable Integer projectId, @PathVariable Integer techId) {
        techService.addTechnologyToProject(projectId, techId);
        ResponseDTO<String> response = new ResponseDTO<>("Technology added to project", "Technology with ID " + techId + " added to project with ID " + projectId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint para obtener proyectos por tecnología
    @GetMapping("/tec/{tech}")
    public ResponseEntity<ResponseDTO<Page<ProjectDTO>>> getProjectsByTechnology(@PathVariable String tech, Pageable pageable) {

        // Llama al servicio para obtener proyectos por tecnología con paginación
        Page<ProjectDTO> projects = projectService.findProjectsByTechnology(tech, pageable);

        // Crear la respuesta con la información paginada
        ResponseDTO<Page<ProjectDTO>> response = new ResponseDTO<>("Projects retrieved successfully", projects);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}