package com.vedruna.servidorporfolio.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


import com.vedruna.servidorporfolio.dto.DeveloperDTO;
import com.vedruna.servidorporfolio.persistance.models.Developer;
import com.vedruna.servidorporfolio.persistance.models.Project;

@Component
public class DeveloperMapper {
    
// Convierte un Developer a DeveloperDTO
public DeveloperDTO developerToDeveloperDTO(Developer developer) {
    if (developer == null) {
        return null;
    }

    DeveloperDTO developerDTO = new DeveloperDTO();
    developerDTO.setDevId(developer.getDevId());
    developerDTO.setDevName(developer.getDevName());
    developerDTO.setDevSurname(developer.getDevSurname());
    developerDTO.setEmail(developer.getEmail());
    developerDTO.setLinkedinUrl(developer.getLinkedinUrl());
    developerDTO.setGithubUrl(developer.getGithubUrl());

    // Convertimos la lista de proyectos en una lista de nombres de proyectos
    if (developer.getProjectsByDeveloper() != null) {
        List<String> projectNames = developer.getProjectsByDeveloper().stream()
                .map(Project::getProjectName)
                .collect(Collectors.toList());
        developerDTO.setProjectNames(projectNames);
    }

    return developerDTO;
}

// Convierte un DeveloperDTO a Developer
public Developer developerDTOToDeveloper(DeveloperDTO developerDTO) {
    if (developerDTO == null) {
        return null;
    }

    Developer developer = new Developer();
    developer.setDevId(developerDTO.getDevId());
    developer.setDevName(developerDTO.getDevName());
    developer.setDevSurname(developerDTO.getDevSurname());
    developer.setEmail(developerDTO.getEmail());
    developer.setLinkedinUrl(developerDTO.getLinkedinUrl());
    developer.setGithubUrl(developerDTO.getGithubUrl());

    
    return developer;
}

// Método opcional para actualizar un Developer a partir de un DeveloperDTO
public void updateDeveloperFromDTO(DeveloperDTO developerDTO, Developer developer) {
    if (developerDTO.getDevName() != null) {
        developer.setDevName(developerDTO.getDevName());
    }
    if (developerDTO.getDevSurname() != null) {
        developer.setDevSurname(developerDTO.getDevSurname());
    }
    if (developerDTO.getEmail() != null) {
        developer.setEmail(developerDTO.getEmail());
    }
    if (developerDTO.getLinkedinUrl() != null) {
        developer.setLinkedinUrl(developerDTO.getLinkedinUrl());
    }
    if (developerDTO.getGithubUrl() != null) {
        developer.setGithubUrl(developerDTO.getGithubUrl());
    }
    
}
}
