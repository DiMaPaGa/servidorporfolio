package com.vedruna.servidorporfolio.persistance.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.servidorporfolio.persistance.models.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    /**
     * Busca un estado por su nombre.
     * 
     * Este método busca un estado específico en la base de datos utilizando 
     * el nombre del estado. El nombre del estado debe ser único, y si se 
     * encuentra, se devuelve envuelto en un objeto `Optional`.
     * 
     * @param statusName El nombre del estado que se desea buscar.
     * @return Un objeto `Optional` que contiene el estado encontrado o está vacío si no se encuentra ningún estado con el nombre dado.
     */
    Optional<Status> findByStatusName(String statusName);
    
}
