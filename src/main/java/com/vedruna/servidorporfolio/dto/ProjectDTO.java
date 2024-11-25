package com.vedruna.servidorporfolio.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vedruna.servidorporfolio.validation.EndDateAfterStartDate;
import com.vedruna.servidorporfolio.validation.ValidURL;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EndDateAfterStartDate
public class ProjectDTO {

    @Schema(description = "Unique identifier of the project", example = "101")
    private Integer projectId;

    @NotNull(message = "Project name cannot be null")
    @Size(min = 3, max = 45, message = "Project name must be between 3 and 45 characters")
    @Schema(description = "Name of the project", example = "Portfolio Manager")
    private String projectName;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    @Schema(description = "Description of the project", example = "A web app to manage project portfolios.")
    private String description;

    @PastOrPresent(message = "Start date must be in the past or present.")
    @Schema(description = "Start date of the project", example = "2024-01-15")
    private LocalDate startDate;

    @Schema(description = "End date of the project", example = "2024-12-15")
    private LocalDate endDate;

    @Size(max = 255, message = "Repository URL cannot exceed 255 characters")
    @ValidURL(message = "Repository URL must be a valid URL")
    @Schema(description = "Repository URL of the project", example = "https://github.com/example/project")
    private String repositoryUrl;

    @Size(max = 255, message = "Demo URL cannot exceed 255 characters")
    @ValidURL(message = "Demo URL must be a valid URL")
    @Schema(description = "Demo URL of the project", example = "https://demo.example.com")
    private String demoUrl;

    @Size(max = 255, message = "Picture URL cannot exceed 255 characters")
    @Schema(description = "Picture URL of the project", example = "https://images.example.com/project.jpg")
    private String picture;

    @Schema(description = "Status name of the project", example = "In Development")
    private String statusName;  

    
    @JsonIgnore
    @Schema(description = "List of developer IDs associated with the project", example = "[1, 2, 3]")
    private List<Integer> devIds;

    @JsonIgnore
    @Schema(description = "List of technology IDs associated with the project", example = "[101, 102]")
    private List<Integer> techIds;  

    
    @Schema(description = "List of developers' names associated with the project", example = "[\"Alicia López\", \"Rafael Mancina\"]")
    private List<String> developers;

    @Schema(description = "List of technologies used in the project", example = "[\"Java\", \"Spring Boot\"]")
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
