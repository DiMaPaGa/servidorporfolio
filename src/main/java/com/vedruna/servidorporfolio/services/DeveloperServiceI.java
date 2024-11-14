package com.vedruna.servidorporfolio.services;

import com.vedruna.servidorporfolio.dto.DeveloperDTO;

public interface DeveloperServiceI {

    void saveDeveloper(DeveloperDTO developerDTO);  // Ahora trabajamos con DTO
    void deleteDeveloperById(Integer devId);

    public void addDeveloperToProject(Integer developerId, Integer projectId);
    
}
