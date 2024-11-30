package com.vedruna.servidorporfolio.services;

import org.springframework.stereotype.Service;

import com.vedruna.servidorporfolio.dto.DeveloperDTO;
import com.vedruna.servidorporfolio.exceptions.DeveloperNotFoundException;
import com.vedruna.servidorporfolio.exceptions.ProjectNotFoundException;
import com.vedruna.servidorporfolio.mappers.DeveloperMapper;
import com.vedruna.servidorporfolio.persistance.models.Developer;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.persistance.repositories.DeveloperRepository;
import com.vedruna.servidorporfolio.persistance.repositories.ProjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperServiceI {

    private final DeveloperRepository developerRepo;
    private final ProjectRepository projectRepo;
    private final DeveloperMapper developerMapper;  // Usamos el mapper para la conversión entre DTO y entidad

    /**
     * Guarda un nuevo desarrollador en la base de datos a partir de un DTO.
     * 
     * Este método convierte un objeto DeveloperDTO a una entidad Developer utilizando el mapper,
     * y luego lo guarda en la base de datos a través del repositorio correspondiente.
     * 
     * @param developerDTO El DTO del desarrollador que se desea guardar.
     */
    @Override
    public void saveDeveloper(DeveloperDTO developerDTO) {
        Developer developer = developerMapper.developerDTOToDeveloper(developerDTO);
        developerRepo.save(developer);
    }


     /**
     * Elimina un desarrollador de la base de datos por su ID.
     * 
     * Este método verifica si el desarrollador con el ID especificado existe. Si no existe,
     * lanza una excepción DeveloperNotFoundException. Si existe, procede a eliminarlo.
     * 
     * @param devId El ID del desarrollador que se desea eliminar.
     * @throws DeveloperNotFoundException Si el desarrollador no existe en la base de datos.
     */
    @Override
    public void deleteDeveloperById(Integer devId) {
        if (!developerRepo.existsById(devId)) {
            throw new DeveloperNotFoundException("Developer with ID " + devId + " not found.");
        }
        developerRepo.deleteById(devId);
    }

    /**
     * Asocia un desarrollador existente a un proyecto existente en la base de datos.
     * 
     * Este método busca al desarrollador y al proyecto por sus respectivos IDs. Si alguno de ellos no se encuentra,
     * lanza las excepciones correspondientes. Luego, agrega el proyecto a la lista de proyectos del desarrollador 
     * y el desarrollador a la lista de desarrolladores del proyecto, asegurando la relación bidireccional.
     * Finalmente, guarda ambos lados de la relación en la base de datos.
     * 
     * @param developerId El ID del desarrollador que se desea asociar al proyecto.
     * @param projectId El ID del proyecto al que se desea asociar al desarrollador.
     * @throws DeveloperNotFoundException Si el desarrollador no existe en la base de datos.
     * @throws ProjectNotFoundException Si el proyecto no existe en la base de datos.
     */
    @Override
public void addDeveloperToProject(Integer developerId, Integer projectId) {
    Developer developer = developerRepo.findById(developerId)
            .orElseThrow(() -> new DeveloperNotFoundException("Developer with ID " + developerId + " not found."));

    Project project = projectRepo.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found."));

    // Agregar el proyecto a la lista de proyectos del desarrollador
    if (!developer.getProjectsByDeveloper().contains(project)) {
        developer.getProjectsByDeveloper().add(project);
    }

    // Agregar el desarrollador a la lista de desarrolladores del proyecto
    if (!project.getDevelopers().contains(developer)) {
        project.getDevelopers().add(developer);
    }

    // Guardar ambos lados de la relación para asegurar la persistencia
    developerRepo.save(developer);
    projectRepo.save(project);
}


}
