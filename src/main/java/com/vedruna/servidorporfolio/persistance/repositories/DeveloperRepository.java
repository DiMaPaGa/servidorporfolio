package com.vedruna.servidorporfolio.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vedruna.servidorporfolio.persistance.models.Developer;

/**
 * Repositorio de la entidad Developer.
 * 
 * Esta interfaz extiende JpaRepository, proporcionando métodos CRUD básicos
 * para trabajar con la entidad Developer. También contiene métodos personalizados
 * para buscar desarrolladores basados en el ID de un proyecto y por nombre.
 * 
 * @author [Diana Mª Pascual García]
 */
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    
    /**
     * Encuentra los desarrolladores asociados a un proyecto específico.
     * 
     * Este método utiliza una consulta personalizada para obtener una lista
     * de desarrolladores que están trabajando en el proyecto con el ID especificado.
     * 
     * @param projectId El ID del proyecto.
     * @return Una lista de desarrolladores asociados al proyecto.
     */
    @Query("SELECT d FROM Developer d JOIN d.projectsByDeveloper p WHERE p.projectId = :projectId")

    /**
     * Encuentra los desarrolladores asociados a un proyecto específico.
     * 
     * Este método utiliza una consulta personalizada para obtener una lista
     * de desarrolladores que están trabajando en el proyecto con el ID especificado.
     * 
     * @param projectId El ID del proyecto.
     * @return Una lista de desarrolladores asociados al proyecto.
     */
    List<Developer> findByProjectId(Integer projectId);
    
    /**
     * Encuentra desarrolladores cuyos nombres coinciden con alguno de los nombres proporcionados.
     * 
     * Este método busca desarrolladores en función de una lista de nombres. Retorna
     * una lista de desarrolladores cuyos nombres están en la lista proporcionada.
     * 
     * @param devNames Una lista de nombres de desarrolladores.
     * @return Una lista de desarrolladores que coinciden con los nombres proporcionados.
     */
    List<Developer> findByDevNameIn(List<String> devNames);


    
}
