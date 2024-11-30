package com.vedruna.servidorporfolio.persistance.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una tecnología utilizada en los proyectos.
 * 
 * Esta entidad almacena información sobre las tecnologías que pueden ser utilizadas
 * en uno o más proyectos. Las tecnologías son asociadas a los proyectos a través de
 * una relación de muchos a muchos.
 * 
 * @author [Diana Mª Pascual García]
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "technologies")
public class Technology implements Serializable{
    
    /**
     * Identificador único de la tecnología.
     * 
     * Este campo es la clave primaria de la tabla "technologies" y se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id", nullable = false)
    private Integer techId;

    /**
     * Nombre de la tecnología.
     * 
     * Este campo almacena el nombre de la tecnología, como "Java", "Python", etc.
     * Es único, pero puede ser nulo.
     */
    @Column(name = "tech_name", length = 45, unique = true, nullable = true)
    private String techName;

    /**
     * Lista de proyectos que utilizan esta tecnología.
     * 
     * Cada tecnología puede estar asociada a varios proyectos, y esta relación se
     * maneja mediante una tabla intermedia "technologies_used_in_projects". La relación
     * es de muchos a muchos y se configura para realizar operaciones en cascada como
     * persistir, fusionar y eliminar los registros relacionados.
     */
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "technologies_used_in_projects", joinColumns = {@JoinColumn(name = "technologies_tech_id")}, inverseJoinColumns = {@JoinColumn(name = "projects_project_id")})
    private List<Project> projectsByTechnology;

}
