package com.vedruna.servidorporfolio.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


import com.vedruna.servidorporfolio.dto.ProjectDTO;

import com.vedruna.servidorporfolio.exceptions.DeveloperNotFoundException;
import com.vedruna.servidorporfolio.exceptions.ProjectNotFoundException;
import com.vedruna.servidorporfolio.exceptions.StatusNotFoundException;
import com.vedruna.servidorporfolio.exceptions.TechnologyNotFoundException;
import com.vedruna.servidorporfolio.mappers.ProjectMapper;
import com.vedruna.servidorporfolio.persistance.models.Developer;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.persistance.models.Status;
import com.vedruna.servidorporfolio.persistance.models.Technology;
import com.vedruna.servidorporfolio.persistance.repositories.DeveloperRepository;
import com.vedruna.servidorporfolio.persistance.repositories.ProjectRepository;
import com.vedruna.servidorporfolio.persistance.repositories.StatusRepository;
import com.vedruna.servidorporfolio.persistance.repositories.TechnologyRepository;

import lombok.AllArgsConstructor;

/**
* Implementación del servicio de gestión de proyectos.
 * Maneja la lógica de negocio para operaciones relacionadas con los proyectos.
 */
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectServiceI {

    
    private final ProjectRepository projectRepo;
    private final StatusRepository statusRepo;
    private final TechnologyRepository technologyRepo;
    private final DeveloperRepository developerRepo;
    private final ProjectMapper projectMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ProjectDTO> getAllProjects(Pageable pageable) {
        // Obtiene una lista paginada de todos los proyectos con sus desarrolladores y tecnologías asociadas.
        Page<Project> projects = projectRepo.findAll(pageable);
        return projects.map(project -> {
            ProjectDTO projectDTO = projectMapper.projectToProjectDTO(project);

            // Obtener desarrolladores asociados al proyecto y añadir al DTO
            List<String> developers = developerRepo.findByProjectId(project.getProjectId()).stream()
                                                .map(dev -> dev.getDevName() + " " + dev.getDevSurname())
                                                .collect(Collectors.toList());
            projectDTO.setDevelopers(developers);

            // Obtener tecnologías asociadas al proyecto y añadir al DTO
            List<String> technologies = technologyRepo.findByProjectId(project.getProjectId()).stream()
                                                    .map(Technology::getTechName)
                                                    .collect(Collectors.toList());
            projectDTO.setTechnologies(technologies);

            return projectDTO;
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ProjectDTO> getProjectsByNameContaining(String word, Pageable pageable) {
        // Busca proyectos por palabra clave en el nombre y los convierte a DTOs.
        Page<Project> projects = projectRepo.findByProjectNameContaining(word, pageable);
        return projects.map(projectMapper::projectToProjectDTO); // Convertir entidades a DTOs
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        // Crea un nuevo proyecto a partir del DTO y lo guarda en la base de datos.
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setRepositoryUrl(projectDTO.getRepositoryUrl());
        project.setDemoUrl(projectDTO.getDemoUrl());
        project.setPicture(projectDTO.getPicture());

        // Asignación de estado
        if (projectDTO.getStatusName() != null && !projectDTO.getStatusName().isEmpty()) {
            Status status = statusRepo.findByStatusName(projectDTO.getStatusName())
                .orElseThrow(() -> new StatusNotFoundException("Status not found with name: " + projectDTO.getStatusName()));
            project.setStatus(status);
        }

        // Guardar el proyecto
        project = projectRepo.save(project);

        // Convertir el proyecto guardado a DTO para la respuesta
        ProjectDTO savedProjectDTO = projectMapper.projectToProjectDTO(project);

        // Asegúrate de que los developers y technologies estén vacíos si no se asignaron
        savedProjectDTO.setDevelopers(Collections.emptyList());
        savedProjectDTO.setTechnologies(Collections.emptyList());

        return savedProjectDTO;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectDTO updateProject(Integer projectId, ProjectDTO projectDTO) {
        // Verificar si el proyecto existe
        Project existingProject = projectRepo.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found."));

        // Actualizar el Project usando el Mapper y los repositorios para resolver las relaciones complejas
        projectMapper.updateProjectFromDTO(projectDTO, existingProject, statusRepo, developerRepo, technologyRepo);

        // Guardar el proyecto actualizado
        Project updatedProject = projectRepo.save(existingProject);

        // Convertir el proyecto actualizado a DTO y devolverlo
        return projectMapper.projectToProjectDTO(updatedProject);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Project> deleteProject(Integer projectId) {
        // Elimina un proyecto por su ID, si existe.
        Optional<Project> project = projectRepo.findById(projectId);
        if (project.isPresent()) {
            projectRepo.delete(project.get());
        } return project;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveProjectToTesting(Integer projectId) {
        updateProjectStatus(projectId, "Testing");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveProjectToProduction(Integer projectId) {
        updateProjectStatus(projectId, "Production");
    }


    /**
     * Actualiza el estado de un proyecto al estado especificado.
     * 
     * @param projectId El ID del proyecto.
     * @param statusName El nombre del nuevo estado.
     */
    private void updateProjectStatus(Integer projectId, String statusName) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException ("Project with ID " + projectId + " not found."));
        
        Status status = statusRepo.findByStatusName(statusName)
                .orElseThrow(() -> new StatusNotFoundException("Status " + statusName + " not found."));
        
        project.setStatus(status);
        projectRepo.save(project);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ProjectDTO> findProjectsByTechnology(String techName, Pageable pageable) {
        // Busca proyectos por tecnología y los convierte a DTOs.
        Page<Project> projects = projectRepo.findProjectsByTechnology(techName, pageable);
        return projects.map(projectMapper::projectToProjectDTO); // Convertir entidades a DTOs
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTechnologyToProject(Integer projectId, Integer techId) {
        // Agrega una tecnología a un proyecto
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException ("Project with ID " + projectId + " not found."));
        
        Technology technology = technologyRepo.findById(techId)
                .orElseThrow(() -> new TechnologyNotFoundException("Technology with ID " + techId + " not found."));
        
        project.getTechnologies().add(technology);
        projectRepo.save(project);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDeveloperToProject(Integer projectId, Integer developerId) {
        // Asocia un desarrollador a un proyecto.
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found."));
        
        Developer developer = developerRepo.findById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer with ID " + developerId + " not found."));
        
        project.getDevelopers().add(developer);
        projectRepo.save(project);
    }

}

   
