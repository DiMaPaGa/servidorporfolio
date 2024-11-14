package com.vedruna.servidorporfolio.dto;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Integer projectId;

    @NotNull(message = "Project name cannot be null")
    @Size(min = 1, max = 45, message = "Project name must be between 3 and 45 characters")
    private String projectName;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    private Date startDate;

    private Date endDate;

    @Size(max = 255, message = "Repository URL cannot exceed 255 characters")
    @Pattern(regexp = "^https?://.*", message = "Repository URL must be a valid URL")
    private String repositoryUrl;

    @Size(max = 255, message = "Demo URL cannot exceed 255 characters")
    @Pattern(regexp = "^https?://.*", message = "Demo URL must be a valid URL")
    private String demoUrl;

    @Size(max = 255, message = "Picture URL cannot exceed 255 characters")
    private String picture;

    @NotNull(message = "Project status cannot be null")
    private String statusName;  // Nombre del estado del proyecto

    // IDs que se usan para crear/actualizar el proyecto
    private List<Integer> devIds;
    private List<Integer> techIds;  

    // Nombres que se devuelven en la respuesta
    private List<String> developers;
    private List<String> technologies;

    @JsonIgnore
    public List<Integer> getDevIds() {
        return devIds;
    }

    @JsonIgnore
    public List<Integer> getTechIds() {
        return techIds;
    }
    
    
}
