package com.vedruna.servidorporfolio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    @Schema(description = "Message that describes the outcome of the request", example = "Developer created successfully")
    private String message;

    @Schema(description = "Data associated with the response, can be any type depending on the operation", example = "{\"devId\": 1, \"devName\": \"Jose Carlos\"}")
    private T data;
    
}
