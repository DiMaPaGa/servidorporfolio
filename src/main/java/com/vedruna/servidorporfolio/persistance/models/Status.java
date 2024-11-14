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

@EqualsAndHashCode(exclude = {"projects"})
@ToString(exclude = {"projects"})
@NoArgsConstructor
@Data
@Entity
@Table(name = "status")

public class Status implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    @Column(name = "status_name", length = 20, unique = true, nullable = false)
    private String statusName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private List<Project> projects;

}
