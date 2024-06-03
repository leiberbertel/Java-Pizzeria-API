package com.leiber.pizza.services.dto;

import lombok.Data;

/**
 * DTO para generar las órdenes aleatorias.
 * @author Leiber Bertel
 */
@Data
public class RamdonOrderDto {
    private String idCustomer;
    private String method;
}
