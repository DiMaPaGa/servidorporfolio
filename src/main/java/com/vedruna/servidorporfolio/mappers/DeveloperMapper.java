package com.vedruna.servidorporfolio.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


import com.vedruna.servidorporfolio.dto.DeveloperDTO;
import com.vedruna.servidorporfolio.persistance.models.Developer;
import com.vedruna.servidorporfolio.persistance.models.Project;


/**
 * Clase que se encarga de realizar el mapeo entre objetos {@link Developer} y {@link DeveloperDTO}.
 * Este componente convierte los objetos de la capa de persistencia (modelo) en objetos de transferencia 
 * de datos (DTO) y viceversa, permitiendo una comunicación adecuada entre las capas de la aplicación.
 * 
 * @author [Diana Mª Pascual García]
 * @version 1.0
 */
@Component
public class DeveloperMapper {

/**
* Convierte un objeto {@link Developer} en un objeto {@link DeveloperDTO}.
* 
* @param developer El objeto {@link Developer} que se desea convertir.
* @return Un objeto {@link DeveloperDTO} con los datos del desarrollador.
*/
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

/**
     * Convierte un objeto {@link DeveloperDTO} en un objeto {@link Developer}.
     * 
     * @param developerDTO El objeto {@link DeveloperDTO} que se desea convertir.
     * @return Un objeto {@link Developer} con los datos del desarrollador.
     */
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

/**
     * Actualiza un objeto {@link Developer} a partir de un objeto {@link DeveloperDTO}.
     * Este método permite modificar las propiedades de un desarrollador existente basándose en los valores 
     * proporcionados en el DTO.
     * 
     * @param developerDTO El objeto {@link DeveloperDTO} con los nuevos datos del desarrollador.
     * @param developer El objeto {@link Developer} que se desea actualizar.
     */
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
