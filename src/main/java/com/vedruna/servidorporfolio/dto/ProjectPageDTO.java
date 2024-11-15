package com.vedruna.servidorporfolio.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPageDTO {
    @Schema(description = "List of projects on the current page", example = "[{\"projectId\": 1, \"projectName\": \"Portfolio Manager\", \"description\": \"A web app to manage project portfolios.\"}]")
    private List<ProjectDTO> projects; 

    @Schema(description = "Total number of elements across all pages", example = "30")
    private long totalElements;         

    @Schema(description = "Total number of pages", example = "2")
    private int totalPages;
    
    @Schema(description = "Current page number", example = "1")
    private int currentPage;  
    
    @Schema(description = "Size of each page", example = "10")
    private int pageSize;               
    
}
