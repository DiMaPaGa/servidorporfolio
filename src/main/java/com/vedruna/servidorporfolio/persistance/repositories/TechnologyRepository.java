package com.vedruna.servidorporfolio.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vedruna.servidorporfolio.persistance.models.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

    /**
     * Busca las tecnologías asociadas a un proyecto por su ID.
     * 
     * Este método consulta la base de datos para obtener todas las tecnologías
     * que están asociadas a un proyecto específico, utilizando el ID del proyecto.
     * 
     * @param projectId El ID del proyecto cuyos tecnologías se desean obtener.
     * @return Una lista de tecnologías asociadas al proyecto con el ID proporcionado.
     */
    @Query("SELECT t FROM Technology t JOIN t.projectsByTechnology p WHERE p.projectId = :projectId")
    List<Technology> findByProjectId(Integer projectId);

    /**
     * Busca tecnologías cuyos nombres están en una lista dada.
     * 
     * Este método permite obtener todas las tecnologías cuyos nombres están 
     * en la lista proporcionada. Es útil cuando se necesitan buscar varias 
     * tecnologías a la vez por sus nombres.
     * 
     * @param techNames Una lista de nombres de tecnologías que se desean buscar.
     * @return Una lista de tecnologías cuyo nombre está en la lista proporcionada.
     */
    List<Technology> findByTechNameIn(List<String> techNames);  
}
