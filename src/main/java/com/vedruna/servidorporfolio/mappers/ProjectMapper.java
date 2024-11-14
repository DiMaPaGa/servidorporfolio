package com.vedruna.servidorporfolio.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.exceptions.StatusNotFoundException;
import com.vedruna.servidorporfolio.persistance.models.Developer;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.persistance.models.Status;
import com.vedruna.servidorporfolio.persistance.models.Technology;
import com.vedruna.servidorporfolio.persistance.repositories.DeveloperRepository;
import com.vedruna.servidorporfolio.persistance.repositories.StatusRepository;
import com.vedruna.servidorporfolio.persistance.repositories.TechnologyRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ProjectMapper {

    // Convierte un Project a ProjectDTO
public ProjectDTO projectToProjectDTO(Project project) {
    if (project == null) {
        return null;
    }



    ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setProjectId(project.getProjectId());
    projectDTO.setProjectName(project.getProjectName());
    projectDTO.setDescription(project.getDescription());          // Agrega el campo description
    projectDTO.setStartDate(project.getStartDate());              // Agrega el campo startDate
    projectDTO.setEndDate(project.getEndDate());                  // Agrega el campo endDate
    projectDTO.setRepositoryUrl(project.getRepositoryUrl());      // Agrega el campo repositoryUrl
    projectDTO.setDemoUrl(project.getDemoUrl());                  // Agrega el campo demoUrl
    projectDTO.setPicture(project.getPicture());                  // Agrega el campo picture

    if (project.getStatus() != null) {
        projectDTO.setStatusName(project.getStatus().getStatusName());
    }

    // Asignar tecnologías si existen, de lo contrario inicializar la lista vacía
    if (project.getTechnologies() != null && !project.getTechnologies().isEmpty()) {
        List<String> technologies = project.getTechnologies().stream()
                .map(Technology::getTechName)
                .collect(Collectors.toList());
        projectDTO.setTechnologies(technologies);
    } else {
        projectDTO.setTechnologies(Collections.emptyList());
    }

    // Asignar desarrolladores si existen, de lo contrario inicializar la lista vacía
    if (project.getDevelopers() != null && !project.getDevelopers().isEmpty()) {
        List<String> developers = project.getDevelopers().stream()
                .map(dev -> dev.getDevName() + " " + dev.getDevSurname())
                .collect(Collectors.toList());
        projectDTO.setDevelopers(developers);
    } else {
        projectDTO.setDevelopers(Collections.emptyList());
    }

    return projectDTO;
}
    // Convierte un ProjectDTO a Project
    public Project projectDTOToProject(ProjectDTO projectDTO,
                                      DeveloperRepository developerRepo,
                                      TechnologyRepository technologyRepo,
                                      StatusRepository statusRepo) {
        if (projectDTO == null) {
            return null;
        }
    
        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());      // Asignar description
        project.setStartDate(projectDTO.getStartDate());          // Asignar startDate
        project.setEndDate(projectDTO.getEndDate());              // Asignar endDate
        project.setRepositoryUrl(projectDTO.getRepositoryUrl());  // Asignar repositoryUrl
        project.setDemoUrl(projectDTO.getDemoUrl());              // Asignar demoUrl
        project.setPicture(projectDTO.getPicture());              // Asignar picture
    
        // Asignar estado (Status) basado en el nombre de estado y asignar por defecto
        if (projectDTO.getStatusName() != null && !projectDTO.getStatusName().isEmpty()) {
            // Buscar el estado en la base de datos solo si el nombre está presente
            Status status = statusRepo.findByStatusName(projectDTO.getStatusName())
                    .orElseThrow(() -> new StatusNotFoundException("Status not found with name: " + projectDTO.getStatusName()));
            project.setStatus(status);
        } else {
            // Si no se proporciona el estado, asigna el valor por defecto
            Status defaultStatus = new Status();
            defaultStatus.setStatusName("In Development");  // Valor por defecto
            project.setStatus(defaultStatus);
        }
    

        // Asignar tecnologías usando los IDs
        if (projectDTO.getTechIds() != null && !projectDTO.getTechIds().isEmpty()) {
            List<Technology> technologies = technologyRepo.findAllById(projectDTO.getTechIds());
            if (technologies.isEmpty()) {
                throw new EntityNotFoundException("No technologies found with the given IDs.");
            }
            project.setTechnologies(technologies);
        }

        // Asignar desarrolladores usando los IDs
        if (projectDTO.getDevIds() != null && !projectDTO.getDevIds().isEmpty()) {
            List<Developer> developers = developerRepo.findAllById(projectDTO.getDevIds());
            if (developers.isEmpty()) {
                throw new EntityNotFoundException("No developers found with the given IDs.");
            }
            project.setDevelopers(developers);
        }


    return project;  // Devolver el proyecto con las entidades asignadas
}
    // Modificación del método updateProjectFromDTO en ProjectMapper para actualizar campos complejos
    public void updateProjectFromDTO(ProjectDTO projectDTO, Project project, 
                                    StatusRepository statusRepo, 
                                    DeveloperRepository developerRepo, 
                                    TechnologyRepository technologyRepo) {
        // Actualizar nombre del proyecto
        project.setProjectName(projectDTO.getProjectName());

        // Actualizar descripción
        project.setDescription(projectDTO.getDescription());

        // Actualizar fechas de inicio y fin
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());

        // Actualizar URLs (repositorio, demo, imagen)
        project.setRepositoryUrl(projectDTO.getRepositoryUrl());
        project.setDemoUrl(projectDTO.getDemoUrl());
        project.setPicture(projectDTO.getPicture());

        // Actualizar el Status si está en el DTO
        if (projectDTO.getStatusName() != null) {
            Status status = statusRepo.findByStatusName(projectDTO.getStatusName())
                .orElseThrow(() -> new StatusNotFoundException("Status not found with name: " + projectDTO.getStatusName()));
            project.setStatus(status);
        }

        // Actualizar los Developers usando sus IDs
        if (projectDTO.getDevIds() != null && !projectDTO.getDevIds().isEmpty()) {
            List<Developer> developers = developerRepo.findAllById(projectDTO.getDevIds());
            if (developers.isEmpty()) {
                throw new EntityNotFoundException("Developers not found.");
            }
            project.setDevelopers(developers);
        }

    // Actualizar las Technologies usando sus IDs
        if (projectDTO.getTechIds() != null && !projectDTO.getTechIds().isEmpty()) {
            List<Technology> technologies = technologyRepo.findAllById(projectDTO.getTechIds());
            if (technologies.isEmpty()) {
                throw new EntityNotFoundException("Technologies not found.");
            }
            project.setTechnologies(technologies);
        }
    }
}
