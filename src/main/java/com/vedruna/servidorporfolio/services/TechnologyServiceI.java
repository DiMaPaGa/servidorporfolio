package com.vedruna.servidorporfolio.services;

import com.vedruna.servidorporfolio.dto.TechnologyDTO;

/**
 * Interfaz para el servicio de gestión de tecnologías.
 * Define las operaciones relacionadas con las tecnologías, incluyendo
 * la creación, eliminación y asociación de tecnologías a proyectos.
 */
public interface TechnologyServiceI {

    /**
     * Asocia una tecnología existente a un proyecto específico.
     *
     * @param projectId El ID del proyecto al que se desea asociar la tecnología.
     * @param techId El ID de la tecnología que se desea asociar al proyecto.
     */
    void addTechnologyToProject(Integer projectId, Integer techId);

    /**
     * Crea una nueva tecnología a partir de los datos proporcionados.
     *
     * @param technologyDTO El objeto DTO que contiene la información de la nueva tecnología.
     */
    void createTechnology(TechnologyDTO technologyDTO);

    /**
     * Elimina una tecnología existente del sistema.
     *
     * @param techId El ID de la tecnología que se desea eliminar.
     */
    void deleteTechnology(Integer techId);
}
