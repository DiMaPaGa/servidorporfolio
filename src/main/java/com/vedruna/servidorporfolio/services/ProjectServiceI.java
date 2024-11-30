package com.vedruna.servidorporfolio.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.servidorporfolio.dto.ProjectDTO;
import com.vedruna.servidorporfolio.persistance.models.Project;

/**
 * Interfaz para gestionar la lógica de negocio relacionada con los proyectos.
 */
public interface ProjectServiceI {
    /**
     * Obtiene una lista paginada de todos los proyectos.
     * 
     * @param pageable Parámetros de paginación.
     * @return Una página de proyectos representados como objetos DTO.
     */
    Page<ProjectDTO> getAllProjects(Pageable pageable);

    /**
     * Busca proyectos cuyo nombre contiene una palabra clave específica.
     * 
     * @param word La palabra clave que se debe buscar en los nombres de los proyectos.
     * @param pageable Parámetros de paginación.
     * @return Una página de proyectos que cumplen con el criterio de búsqueda.
     */
    Page<ProjectDTO> getProjectsByNameContaining(String word, Pageable pageable);

    /**
     * Crea un nuevo proyecto.
     * 
     * @param projectDTO El DTO que contiene los datos del proyecto a crear.
     * @return El proyecto creado representado como un DTO.
     */
    ProjectDTO saveProject(ProjectDTO projectDTO);

    /**
     * Actualiza los datos de un proyecto existente.
     * 
     * @param projectId El ID del proyecto a actualizar.
     * @param projectDTO El DTO con los nuevos datos del proyecto.
     * @return El proyecto actualizado representado como un DTO.
     */
    ProjectDTO updateProject(Integer projectId, ProjectDTO projectDTO);

    /**
     * Elimina un proyecto por su ID.
     * 
     * @param projectId El ID del proyecto que se desea eliminar.
     * @return Un `Optional` que contiene el proyecto eliminado si existía.
     */
    public Optional<Project> deleteProject(Integer projectId);

    /**
     * Cambia el estado de un proyecto a "Testing".
     * 
     * @param projectId El ID del proyecto al que se le cambiará el estado.
     */
    void moveProjectToTesting(Integer projectId);

    /**
     * Cambia el estado de un proyecto a "Production".
     * 
     * @param projectId El ID del proyecto al que se le cambiará el estado.
     */
    void moveProjectToProduction(Integer projectId);

    /**
     * Busca proyectos asociados con una tecnología específica.
     * 
     * @param techName El nombre de la tecnología.
     * @param pageable Parámetros de paginación.
     * @return Una página de proyectos que utilizan la tecnología especificada.
     */
    Page<ProjectDTO> findProjectsByTechnology(String techName, Pageable pageable);

    /**
     * Asocia una tecnología existente a un proyecto existente.
     * 
     * @param projectId El ID del proyecto al que se le asociará la tecnología.
     * @param techId El ID de la tecnología que se asociará al proyecto.
     */
    void addTechnologyToProject(Integer projectId, Integer techId);

    /**
     * Asocia un desarrollador existente a un proyecto existente.
     * 
     * @param projectId El ID del proyecto al que se le asociará el desarrollador.
     * @param devId El ID del desarrollador que se asociará al proyecto.
     */
    void addDeveloperToProject(Integer projectId, Integer devId);
}