package com.EduTech_vm.cl.EduTech_vm.OpenApiConfig;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI eduTechOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EduTech API")
                        .description("Documentación de la API REST para la gestión de los microservicios del catálogo de cursos, usuario y carrito de compras de EduTech")
                        .version("v2.0"));
    }  
}
