package com.leiber.pizza.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la documentación de OpenAPI (Swagger) de la API.
 * 
 * @author Leiber Bertel
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura la instancia de OpenAPI con la información personalizada de la API.
     *
     * @return Una instancia de OpenAPI configurada.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Pizzeria")
                        .version("1.0")
                        .description("API para el funcionamiento básico de una pizzeria con Spring Security")
                        .contact(new Contact()
                                .name("Leiber Bertel")
                                .url("https://leiberbertel.github.io/")));
    }
}
