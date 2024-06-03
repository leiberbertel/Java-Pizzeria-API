package com.leiber.pizza.services.dto;

import lombok.Data;

/**
 * DTO para el inicio de sesión de usuario.
 * @author Leiber Bertel
 */
@Data
public class LoginDto {
    private String username;
    private String password;
}
