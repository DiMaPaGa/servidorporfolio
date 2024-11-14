package com.vedruna.servidorporfolio.services;

import com.vedruna.servidorporfolio.dto.TechnologyDTO;

public interface TechnologyServiceI {

    void addTechnologyToProject(Integer projectId, Integer techId);
    void createTechnology(TechnologyDTO technologyDTO);
    void deleteTechnology(Integer techId);
}
