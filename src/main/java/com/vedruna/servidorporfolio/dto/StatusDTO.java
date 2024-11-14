package com.vedruna.servidorporfolio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private Integer statusId;

    @NotNull(message = "Status name cannot be null")
    @Size(min = 1, max = 20, message = "Status name must be between 3 and 20 characters")
    private String statusName;  // Nombre del estado
    
}
