package com.vedruna.servidorporfolio.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDTO {
    @Schema(description = "Unique identifier for the technology", example = "1")
    private Integer techId;

    @Size(min = 3, max = 45, message = "Technology name must be between 3 and 45 characters")
    @Schema(description = "Name of the technology", example = "Spring Boot")
    private String techName;

    @Schema(description = "List of project names that use this technology", example = "[\"Project A\", \"Project B\"]")
    private List<String> projectNames;  // Nombres de los proyectos que utilizan esta tecnología
    
}
