package com.vedruna.servidorporfolio.persistance.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un proyecto dentro del sistema.
 * Esta clase mapea un proyecto que puede tener múltiples desarrolladores y tecnologías asociadas,
 * y un estado relacionado a través de una relación con la entidad {@link Status}.
 * 
 * La clase está anotada con JPA para mapeo con la base de datos y usa Lombok para la generación 
 * automática de getters, setters y otros métodos comunes.
 * 
 * @author [Diana Mª Pascual García]
 * @version 1.0
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "projects")

public class Project implements Serializable{

    /**
     * Identificador único del proyecto.
     * Este campo es la clave primaria en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    /**
     * Nombre del proyecto.
     * Este campo es único y no puede ser nulo.
     */
    @Column(name = "project_name", length = 45, unique = true, nullable = false)
    private String projectName;

    /**
     * Descripción del proyecto.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "description", nullable = true)
    private String description;

    /**
     * Fecha de inicio del proyecto.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    /**
     * Fecha de finalización del proyecto.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    /**
     * URL del repositorio del proyecto.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "repository_url", length = 255, nullable = true)
    private String repositoryUrl;

    /**
     * URL de la demo del proyecto.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "demo_url", length = 255, nullable = true)
    private String demoUrl;

    /**
     * URL de la imagen asociada al proyecto.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "picture", length = 255, nullable = true)
    private String picture;

    /**
     * Estado del proyecto.
     * Este campo se refiere a la entidad {@link Status} y está vinculado a través de una relación de muchos a uno.
     * No puede ser nulo.
     */
    @ManyToOne(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status_status_id", referencedColumnName = "status_id", nullable = false)
    private Status status;

    /**
     * Lista de desarrolladores asociados al proyecto.
     * La relación es de muchos a muchos con la entidad {@link Developer}, gestionada mediante una tabla intermedia.
     * Esta relación se carga de forma Lazy Loading.
     */
    @ManyToMany(mappedBy = "projectsByDeveloper", fetch = FetchType.LAZY)
    private List<Developer> developers;

    /**
     * Lista de tecnologías asociadas al proyecto.
     * La relación es de muchos a muchos con la entidad {@link Technology}, gestionada mediante una tabla intermedia.
     * Esta relación se carga de forma perezosa (Lazy Loading) y las operaciones de persistencia, 
     * fusión y eliminación se propagan en cascada.
     */
    @ManyToMany(mappedBy = "projectsByTechnology", fetch = FetchType.LAZY)
    private List<Technology> technologies;

    
}
