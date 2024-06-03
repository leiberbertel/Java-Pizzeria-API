package com.leiber.pizza.services.dto;

import lombok.Data;

/**
 * DTO para modificar el precio de una pizza
 * @author Leiber Bertel
 */
@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double newPrice;
}
