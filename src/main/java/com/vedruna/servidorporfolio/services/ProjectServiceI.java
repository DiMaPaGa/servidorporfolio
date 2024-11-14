package com.vedruna.servidorporfolio.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.persistance.models.Project;

public interface ProjectServiceI {
    // Obtener todos los proyectos con paginación
    Page<ProjectDTO> getAllProjects(Pageable pageable);

    // Buscar proyectos por palabra clave en su nombre
    Page<ProjectDTO> getProjectsByNameContaining(String word, Pageable pageable);

    // Crear un nuevo proyecto
    ProjectDTO saveProject(ProjectDTO projectDTO);

    // Actualizar un proyecto existente
    ProjectDTO updateProject(Integer projectId, ProjectDTO projectDTO);

    // Eliminar un proyecto
    public Optional<Project> deleteProject(Integer projectId);

    // Cambiar el estado de un proyecto a "Testing"
    void moveProjectToTesting(Integer projectId);

    // Cambiar el estado de un proyecto a "Production"
    void moveProjectToProduction(Integer projectId);

    // Buscar proyectos por tecnología
    Page<ProjectDTO> findProjectsByTechnology(String techName, Pageable pageable);

    // Asociar una tecnología a un proyecto
    void addTechnologyToProject(Integer projectId, Integer techId);

    // Asociar un desarrollador a un proyecto
    void addDeveloperToProject(Integer projectId, Integer devId);
}