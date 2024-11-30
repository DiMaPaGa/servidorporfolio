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

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


/**
 * Representa a un desarrollador dentro del sistema.
 * Esta entidad contiene información sobre el nombre, apellido, correo electrónico, 
 * y los enlaces a sus perfiles de LinkedIn y GitHub. Además, mapea la relación 
 * de muchos a muchos con los proyectos en los que el desarrollador ha trabajado.
 * 
 * La clase está anotada con JPA para mapeo con la base de datos, y utiliza 
 * Lombok para reducir la cantidad de código repetitivo (getters, setters, etc.).
 * 
 * @author [Diana Mª Pascual García]
 * @version 1.0
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "developers")
public class Developer implements Serializable {

    /**
     * Identificador único del desarrollador.
     * Este campo es la clave primaria en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id", nullable = false)
    private Integer devId;
    
    /**
     * Nombre del desarrollador.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "dev_name", length = 45, nullable = true)
    private String devName;

     /**
     * Apellido del desarrollador.
     * Este campo es opcional y puede ser nulo.
     */
    @Column(name = "dev_surname", length = 45, nullable = true)
    private String devSurname;

    /**
     * Correo electrónico del desarrollador.
     * Este campo es único y validado con la anotación {@link Email}.
     * Puede ser nulo.
     */
    @Email
    @Column(name = "email", length = 255, nullable = true, unique = true)
    private String email;

    /**
     * URL del perfil de LinkedIn del desarrollador.
     * Este campo tiene una longitud máxima de 255 caracteres y puede ser nulo.
     */
    @Size(max = 255)
    @Column(name = "linkedin_url", length = 255, nullable = true, unique = true)
    private String linkedinUrl;

    /**
     * URL del perfil de GitHub del desarrollador.
     * Este campo tiene una longitud máxima de 255 caracteres y puede ser nulo.
     */
    @Size(max = 255)
    @Column(name = "github_url", length = 255, nullable = true, unique = true)
    private String githubUrl;

    /**
     * Lista de proyectos en los que el desarrollador ha trabajado.
     * La relación es de muchos a muchos con la entidad {@link Project}, 
     * y se gestiona mediante una tabla intermedia llamada "developers_worked_on_projects".
     * Las operaciones de persistencia y eliminación de proyectos se realizan en cascada.
     */
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "developers_worked_on_projects", joinColumns = {@JoinColumn(name = "developers_dev_id")}, inverseJoinColumns = {@JoinColumn(name = "projects_project_id")})
    private List<Project> projectsByDeveloper;

    
}
