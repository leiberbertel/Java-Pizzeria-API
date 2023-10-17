package com.leiber.pizza.exception;

import com.leiber.pizza.persistence.entity.PizzaEntity;

public class PizzaAlreadyExistsException extends RuntimeException{
    public PizzaAlreadyExistsException(PizzaEntity pizzaEntity) {
        super("Ya existe la pizza con id: " + pizzaEntity.getIdPizza());
    }
}
