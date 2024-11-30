package com.vedruna.servidorporfolio.services;

import com.vedruna.servidorporfolio.dto.DeveloperDTO;

/**
 * Interfaz para gestionar la lógica de negocio relacionada con los desarrolladores.
 */
public interface DeveloperServiceI {

    /**
     * Guarda un nuevo desarrollador en el sistema a partir de un DTO.
     * 
     * Este método recibe un objeto DTO que contiene los datos del desarrollador 
     * y los guarda en la base de datos.
     * 
     * @param developerDTO El DTO del desarrollador que se desea guardar.
     */
    void saveDeveloper(DeveloperDTO developerDTO);

    /**
     * Elimina un desarrollador del sistema utilizando su ID.
     * 
     * Este método busca y elimina al desarrollador cuyo ID se proporciona 
     * como parámetro.
     * 
     * @param devId El ID del desarrollador que se desea eliminar.
     */
    void deleteDeveloperById(Integer devId);

    /**
     * Asocia a un desarrollador a un proyecto específico.
     * 
     * Este método asocia a un desarrollador identificado por su ID a un 
     * proyecto identificado por su ID. El desarrollador será añadido al proyecto 
     * correspondiente.
     * 
     * @param developerId El ID del desarrollador que se desea añadir al proyecto.
     * @param projectId El ID del proyecto al que se desea asociar al desarrollador.
     */
    public void addDeveloperToProject(Integer developerId, Integer projectId);
    
}
