package com.vedruna.servidorporfolio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperRequestDTO {

    @Schema(description = "ID of the project to associate with the developer", example = "1001")
    private Integer projectId;

    @Schema(description = "ID of the developer to associate with the project", example = "2001")
    private Integer devId;
    
}
