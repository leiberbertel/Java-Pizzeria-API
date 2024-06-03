package com.leiber.pizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Utilitario para manejar operaciones relacionadas con JWT (JSON Web Tokens).
 * 
 * @author Leiber Bertel
 */
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "21318737813674623145678251741231671257184382152517412472158";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    /**
     * Crea un token JWT para el nombre de usuario dado.
     *
     * @param username El nombre de usuario.
     * @return El token JWT generado.
     */
    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("java-pizzeria")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    /**
     * Verifica si un token JWT es válido.
     *
     * @param jwt El token JWT.
     * @return true si el token es válido, false en caso contrario.
     */
    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * Obtiene el nombre de usuario del token JWT.
     *
     * @param jwt El token JWT.
     * @return El nombre de usuario contenido en el token.
     */
    public String getUsername (String jwt) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
