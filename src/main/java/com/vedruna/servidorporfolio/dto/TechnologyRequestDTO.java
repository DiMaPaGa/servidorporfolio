package com.vedruna.servidorporfolio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyRequestDTO {
    @Schema(description = "ID of the project to which the technology is being added", example = "101")
    private Integer projectId;

    @Schema(description = "ID of the technology to be added to the project", example = "5")
    private Integer techId;
    
}
