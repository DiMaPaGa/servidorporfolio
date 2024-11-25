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

@NoArgsConstructor
@Data
@Entity
@Table(name = "developers")
public class Developer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id", nullable = false)
    private Integer devId;
    
    @Column(name = "dev_name", length = 45, nullable = true)
    private String devName;

    @Column(name = "dev_surname", length = 45, nullable = true)
    private String devSurname;

    @Email
    @Column(name = "email", length = 255, nullable = true, unique = true)
    private String email;

    @Size(max = 255)
    @Column(name = "linkedin_url", length = 255, nullable = true, unique = true)
    private String linkedinUrl;

    @Size(max = 255)
    @Column(name = "github_url", length = 255, nullable = true, unique = true)
    private String githubUrl;

    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "developers_worked_on_projects", joinColumns = {@JoinColumn(name = "developers_dev_id")}, inverseJoinColumns = {@JoinColumn(name = "projects_project_id")})
    private List<Project> projectsByDeveloper;

    
}
