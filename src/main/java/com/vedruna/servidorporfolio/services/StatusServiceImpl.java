package com.vedruna.servidorporfolio.services;

import org.springframework.stereotype.Service;

import com.vedruna.servidorporfolio.exceptions.ProjectNotFoundException;
import com.vedruna.servidorporfolio.exceptions.StatusNotFoundException;
import com.vedruna.servidorporfolio.persistance.models.Project;
import com.vedruna.servidorporfolio.persistance.models.Status;
import com.vedruna.servidorporfolio.persistance.repositories.ProjectRepository;
import com.vedruna.servidorporfolio.persistance.repositories.StatusRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusServiceI {

    
    private final ProjectRepository projectRepo;
    private final StatusRepository statusRepo;

    @Override
    public void changeProjectStatus(Integer projectId, String statusName) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + projectId + " not found."));

        Status status = statusRepo.findByStatusName(statusName)
                .orElseThrow(() -> new StatusNotFoundException("Status with name " + statusName + " not found."));

        project.setStatus(status);
        projectRepo.save(project);
    }
    
}
