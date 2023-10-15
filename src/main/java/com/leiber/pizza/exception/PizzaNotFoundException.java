package com.leiber.pizza.exception;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(int id) {
        super("No se ha encontrado la pizza con id: " + id);
    }
}
