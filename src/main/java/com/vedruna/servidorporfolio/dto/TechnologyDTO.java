package com.vedruna.servidorporfolio.dto;

import java.util.List;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDTO {
    private Integer techId;

    @Size(min = 1, max = 45, message = "Technology name must be between 3 and 45 characters")
    private String techName;
    private List<String> projectNames;  // Nombres de los proyectos que utilizan esta tecnología
    
}
