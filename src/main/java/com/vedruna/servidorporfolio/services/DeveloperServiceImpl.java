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

    @Override
    public void saveDeveloper(DeveloperDTO developerDTO) {
        Developer developer = developerMapper.developerDTOToDeveloper(developerDTO);
        developerRepo.save(developer);
    }

    @Override
    public void deleteDeveloperById(Integer devId) {
        if (!developerRepo.existsById(devId)) {
            throw new DeveloperNotFoundException("Developer with ID " + devId + " not found.");
        }
        developerRepo.deleteById(devId);
    }

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
