package com.leiber.pizza.exception;

import com.leiber.pizza.persistence.entity.PizzaEntity;

/**
 * Excepción lanzada cuando se intenta crear una pizza que ya existe en la base de datos.
 *
 * @author Leiber Bertel
 */
public class PizzaAlreadyExistsException extends RuntimeException {

    /**
     * Constructor que crea una nueva excepción con un mensaje detallando el ID de la pizza que ya existe.
     *
     * @param pizzaEntity La entidad de la pizza que ya existe.
     */
    public PizzaAlreadyExistsException(PizzaEntity pizzaEntity) {
        super("Ya existe la pizza con id: " + pizzaEntity.getIdPizza());
    }
}