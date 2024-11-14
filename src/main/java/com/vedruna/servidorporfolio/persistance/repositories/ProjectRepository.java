package com.vedruna.servidorporfolio.persistance.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vedruna.servidorporfolio.persistance.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

     // Método para obtener proyectos paginados
    Page<Project> findAll(Pageable pageable);

    // Método para encontrar proyectos cuyo nombre contiene una palabra específica
    @Query("SELECT p FROM Project p WHERE LOWER(p.projectName) LIKE LOWER(CONCAT('%', :word, '%'))")
    Page<Project> findByProjectNameContaining(String word, Pageable pageable);

    // Método para obtener proyectos por tecnología
    @Query("SELECT p FROM Project p JOIN p.technologies t WHERE t.techName = :techName")
    Page<Project> findProjectsByTechnology(String techName, Pageable pageable);
}
    
