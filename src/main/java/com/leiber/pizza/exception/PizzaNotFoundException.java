package com.leiber.pizza.exception;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException() {
        super("No se ha encontrado la pizza");
    }
}
