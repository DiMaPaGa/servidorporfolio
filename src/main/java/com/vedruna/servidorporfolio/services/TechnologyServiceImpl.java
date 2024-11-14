package com.vedruna.servidorporfolio.services;

import org.springframework.stereotype.Service;

import com.vedruna.servidorporfolio.dto.TechnologyDTO;
import com.vedruna.servidorporfolio.exceptions.ProjectNotFoundException;
import com.vedruna.servidorporfolio.exceptions.TechnologyNotFoundException;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.persistance.models.Technology;
import com.vedruna.servidorporfolio.persistance.repositories.ProjectRepository;
import com.vedruna.servidorporfolio.persistance.repositories.TechnologyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TechnologyServiceImpl implements TechnologyServiceI {

    private final ProjectRepository projectRepo;
    private final TechnologyRepository techRepo;

    @Override
    public void addTechnologyToProject(Integer projectId, Integer techId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found."));

        Technology technology = techRepo.findById(techId)
                .orElseThrow(() -> new TechnologyNotFoundException("Technology with ID " + techId + " not found."));

        // Agregar la tecnología al proyecto y viceversa
        project.getTechnologies().add(technology);
        technology.getProjectsByTechnology().add(project);

        // Guardar cambios en ambas entidades para reflejar la relación
        projectRepo.save(project);
        techRepo.save(technology);
    }

    @Override
    public void createTechnology(TechnologyDTO technologyDTO) {
        Technology technology = new Technology();
        technology.setTechName(technologyDTO.getTechName());
        techRepo.save(technology);
    }

    @Override
    public void deleteTechnology(Integer techId) {
        Technology technology = techRepo.findById(techId)
                .orElseThrow(() -> new TechnologyNotFoundException("Technology with ID " + techId + " not found."));
        techRepo.delete(technology);
    }
}


    

