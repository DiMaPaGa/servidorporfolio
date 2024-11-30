package com.vedruna.servidorporfolio.persistance.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vedruna.servidorporfolio.persistance.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    /**
    * Obtiene una página de proyectos.
    * 
    * Este método permite obtener una lista paginada de proyectos. 
    * Se puede utilizar para gestionar grandes cantidades de proyectos 
    * sin necesidad de cargar todos los resultados de una vez.
    * 
    * @param pageable El objeto Pageable que especifica el número de página y el tamaño de página.
    * @return Una página de proyectos.
    */
    Page<Project> findAll(Pageable pageable);

    /**
     * Busca proyectos cuyo nombre contiene una palabra específica.
     * 
     * Este método realiza una búsqueda en los nombres de los proyectos 
     * para encontrar aquellos que contengan una palabra específica, 
     * ignorando mayúsculas y minúsculas. Los resultados se devuelven en formato paginado.
     * 
     * @param word La palabra o parte del nombre del proyecto que se desea buscar.
     * @param pageable El objeto Pageable que especifica la paginación.
     * @return Una página de proyectos cuyo nombre contiene la palabra especificada.
     */
    @Query("SELECT p FROM Project p WHERE LOWER(p.projectName) LIKE LOWER(CONCAT('%', :word, '%'))")
    Page<Project> findByProjectNameContaining(String word, Pageable pageable);

    /**
     * Obtiene proyectos que utilizan una tecnología específica.
     * 
     * Este método permite buscar proyectos que utilizan una tecnología 
     * específica, según el nombre de la tecnología. Los resultados se devuelven de manera paginada.
     * 
     * @param techName El nombre de la tecnología que se desea buscar.
     * @param pageable El objeto Pageable que especifica el número de página y el tamaño de página.
     * @return Una página de proyectos que utilizan la tecnología especificada.
     */
    @Query("SELECT p FROM Project p JOIN p.technologies t WHERE t.techName = :techName")
    Page<Project> findProjectsByTechnology(String techName, Pageable pageable);
}
    
