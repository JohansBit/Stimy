package com.duoc.stimy.gamehub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stimy Gamehub - Módulo de Pagos ")
                        .version("1.0.0")
                        .description("Endpoints dedicados a la gestión y consulta de pagos de los Usuarios ")
                        .contact(new Contact()
                                .name("Soporte Stimy")
                                .email("soporte@stimy.cl")));




    }
}
