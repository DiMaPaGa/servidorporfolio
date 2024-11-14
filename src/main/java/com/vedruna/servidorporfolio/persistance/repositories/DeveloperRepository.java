package com.vedruna.servidorporfolio.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vedruna.servidorporfolio.persistance.models.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    @Query("SELECT d FROM Developer d JOIN d.projectsByDeveloper p WHERE p.projectId = :projectId")
    List<Developer> findByProjectId(Integer projectId);
    List<Developer> findByDevNameIn(List<String> devNames);


    
}
