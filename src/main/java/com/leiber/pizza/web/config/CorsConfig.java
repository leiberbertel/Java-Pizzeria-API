package com.leiber.pizza.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de CORS para la aplicación.
 * Permite todas las solicitudes de cualquier origen y todos los métodos HTTP.
 *
 * @author Leiber Bertel
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configura los mapeos de CORS.
     * Permite todas las solicitudes de cualquier origen y todos los métodos HTTP.
     *
     * @param registry El registro de mapeos de CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
