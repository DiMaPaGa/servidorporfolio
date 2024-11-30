package com.vedruna.servidorporfolio.services;

/**
 * Interfaz para el servicio de gestión de estados de proyectos.
 * 
 */
public interface StatusServiceI {

     /**
     * Cambia el estado de un proyecto al estado especificado.
     * 
     * @param projectId El ID del proyecto cuyo estado se va a cambiar.
     * @param statusName El nombre del nuevo estado a asignar al proyecto.
     *                   Debe ser un estado válido existente en el sistema.
     */
    void changeProjectStatus(Integer projectId, String statusName);
        
}
