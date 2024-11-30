package com.vedruna.servidorporfolio.services;

import org.springframework.stereotype.Service;

import com.vedruna.servidorporfolio.exceptions.ProjectNotFoundException;
import com.vedruna.servidorporfolio.exceptions.StatusNotFoundException;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.persistance.models.Status;
import com.vedruna.servidorporfolio.persistance.repositories.ProjectRepository;
import com.vedruna.servidorporfolio.persistance.repositories.StatusRepository;

import lombok.AllArgsConstructor;

/**
 * Implementación del servicio para gestionar los estados de los proyectos.
 */
@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusServiceI {

    /** Repositorio para gestionar la persistencia de los proyectos. */
    private final ProjectRepository projectRepo;
    /** Repositorio para gestionar la persistencia de los estados. */
    private final StatusRepository statusRepo;


     /**
     * Cambia el estado de un proyecto al estado especificado.
     * 
     * @param projectId El ID del proyecto cuyo estado se desea cambiar.
     * @param statusName El nombre del nuevo estado a asignar al proyecto.
     *                   Debe corresponder a un estado válido existente en el sistema.
     * @throws ProjectNotFoundException Si no se encuentra un proyecto con el ID especificado.
     * @throws StatusNotFoundException Si no se encuentra un estado con el nombre especificado.
     */
    @Override
    public void changeProjectStatus(Integer projectId, String statusName) {
        // Buscar el proyecto por su ID
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found."));

        // Buscar el estado por su nombre
        Status status = statusRepo.findByStatusName(statusName)
                .orElseThrow(() -> new StatusNotFoundException("Status with name " + statusName + " not found."));

        // Asignar el nuevo estado al proyecto y guardarlo
        project.setStatus(status);
        projectRepo.save(project);
    }
    
}
