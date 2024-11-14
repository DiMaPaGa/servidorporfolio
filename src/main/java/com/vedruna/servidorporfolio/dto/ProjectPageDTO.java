package com.vedruna.servidorporfolio.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPageDTO {
    private List<ProjectDTO> projects;  // Lista de proyectos
    private long totalElements;         // Total de elementos
    private int totalPages;             // Número total de páginas
    private int currentPage;            // Página actual
    private int pageSize;               // Tamaño de la página
    
}
