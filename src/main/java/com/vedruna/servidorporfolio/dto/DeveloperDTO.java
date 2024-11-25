package com.vedruna.servidorporfolio.dto;

import java.util.List;

import com.vedruna.servidorporfolio.validation.ValidURL;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {
    @Schema(description = "ID of the developer", example = "1")
    private Integer devId;

    @Schema(description = "Name of the developer", example = "Joaquin", 
            minLength = 3, maxLength = 45)
    @Size(min = 3, max = 45, message = "Developer name must be between 3 and 45 characters")
    private String devName;

    @Schema(description = "Surname of the developer", example = "Borrego", 
            minLength = 3, maxLength = 45)
    @Size(min = 3, max = 45, message = "Developer surname must be between 3 and 45 characters")
    private String devSurname;


    @Schema(description = "Email of the developer", example = "johndoe@example.com", 
            maxLength = 255)
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    private String email;

    @Schema(description = "LinkedIn URL of the developer", example = "https://www.linkedin.com/in/johndoe", 
            maxLength = 255)
    @Size(max = 255, message = "LinkedIn URL cannot exceed 255 characters")
    @ValidURL(message = "LinkedIn URL must be a valid URL")
    private String linkedinUrl;

    @Schema(description = "GitHub URL of the developer", example = "https://github.com/johndoe", 
            maxLength = 255)
    @Size(max = 255, message = "GitHub URL cannot exceed 255 characters")
    @ValidURL(message = "GitHub URL must be a valid URL")
    private String githubUrl;
    
    @ArraySchema(schema = @Schema(description = "List of project names the developer has worked on", example = "Project A"))
    private List<String> projectNames;
    
}
