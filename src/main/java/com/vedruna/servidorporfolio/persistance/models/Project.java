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

@NoArgsConstructor
@Data
@Entity
@Table(name = "projects")

public class Project implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "project_name", length = 45, unique = true, nullable = false)
    private String projectName;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @Column(name = "repository_url", length = 255, nullable = true)
    private String repositoryUrl;

    @Column(name = "demo_url", length = 255, nullable = true)
    private String demoUrl;

    @Column(name = "picture", length = 255, nullable = true)
    private String picture;

    @ManyToOne(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "status_status_id", referencedColumnName = "status_id", nullable = false)
    private Status status;

    @ManyToMany(mappedBy = "projectsByDeveloper", fetch = FetchType.LAZY)
    private List<Developer> developers;

    @ManyToMany(mappedBy = "projectsByTechnology", fetch = FetchType.LAZY)
    private List<Technology> technologies;

    
}
