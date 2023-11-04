package com.leiber.pizza.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


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
