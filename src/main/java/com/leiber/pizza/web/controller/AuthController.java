package com.leiber.pizza.web.controller;

import com.leiber.pizza.services.dto.LoginDto;
import com.leiber.pizza.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller para manejar la autenticación de usuarios.
 * Proporciona endpoints para el inicio de sesión y la generación de tokens JWT.
 *
 * @author Leiber Bertel
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    
    /**
     * Constructor para inyectar las dependencias de AuthenticationManager y JwtUtil.
     *
     * @param authenticationManager El administrador de autenticación.
     * @param jwtUtil El utilitario para manejar JWT.
     */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Endpoint para realizar el login de un usuario.
     * Autentica al usuario y genera un token JWT.
     *
     * @param loginDto Los datos de inicio de sesión del usuario.
     * @return Una respuesta HTTP con el token JWT en el encabezado de autorización.
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        logger.info("User authenticated: {}", authentication.isAuthenticated());
        logger.info("User principal: {}", authentication.getPrincipal());

        String jwt = this.jwtUtil.create(loginDto.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
