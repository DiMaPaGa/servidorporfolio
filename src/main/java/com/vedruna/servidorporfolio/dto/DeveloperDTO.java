package com.vedruna.servidorporfolio.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {
    private Integer devId;

    @Size(min = 1, max = 45, message = "Developer name must be between 3 and 45 characters")
    private String devName;

    @Size(min = 1, max = 45, message = "Developer surname must be between 3 and 45 characters")
    private String devSurname;

    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    private String email;

    @Size(max = 255, message = "LinkedIn URL cannot exceed 255 characters")
    @Pattern(regexp = "^https?://.*", message = "LinkedIn URL must be a valid URL")
    private String linkedinUrl;

    @Size(max = 255, message = "GitHub URL cannot exceed 255 characters")
    @Pattern(regexp = "^https?://.*", message = "GitHub URL must be a valid URL")
    private String githubUrl;
    private List<String> projectNames;  // Nombres de los proyectos en los que ha trabajado
    
}
