package com.vedruna.servidorporfolio.persistance.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Representa el estado de un proyecto.
 * 
 * Esta entidad se utiliza para almacenar información sobre el estado de los proyectos,
 * como su nombre y las relaciones con los proyectos asociados.
 * 
 * @author [Diana Mª Pascual García]
 */
@EqualsAndHashCode(exclude = {"projects"})
@ToString(exclude = {"projects"})
@NoArgsConstructor
@Data
@Entity
@Table(name = "status")

public class Status implements Serializable{

    /**
     * Identificador único del estado.
     * 
     * Este campo se utiliza como clave primaria de la tabla "status" y se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    /**
     * Nombre del estado del proyecto.
     * 
     * Este campo almacena el nombre del estado, como "En desarrollo", "Finalizado", etc.
     * Es único y no puede ser nulo.
     */
    @Column(name = "status_name", length = 20, unique = true, nullable = false)
    private String statusName;

    /**
     * Lista de proyectos asociados a este estado.
     * 
     * Cada proyecto puede tener un solo estado. Esta relación se carga de forma perezosa
     * (Lazy Loading), lo que significa que los proyectos asociados no se cargarán hasta
     * que se acceda a esta propiedad.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private List<Project> projects;

}
