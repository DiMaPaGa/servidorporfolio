package com.vedruna.servidorporfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My Portfolio API")
                        .description("API for managing developers, projects and technologies.")
                        .version("v1.0.0")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                            .name("Diana Mª Pascual García")
                            .email("dianamariapascual@gmail.com")));
    }
}


