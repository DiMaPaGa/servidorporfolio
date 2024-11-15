package com.vedruna.servidorporfolio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    @Schema(description = "Unique identifier for the status", example = "1")
    private Integer statusId;

    @NotNull(message = "Status name cannot be null")
    @Size(min = 3, max = 20, message = "Status name must be between 3 and 20 characters")
    @Schema(description = "Name of the status", example = "In Progress")
    private String statusName;  // Nombre del estado
    
}
