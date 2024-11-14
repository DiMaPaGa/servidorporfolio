package com.vedruna.servidorporfolio.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vedruna.servidorporfolio.persistance.models.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {
    @Query("SELECT t FROM Technology t JOIN t.projectsByTechnology p WHERE p.projectId = :projectId")
    List<Technology> findByProjectId(Integer projectId);
    List<Technology> findByTechNameIn(List<String> techNames);  
}
