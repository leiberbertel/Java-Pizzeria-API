package com.leiber.pizza.errors;

public class PizzaWithIdNullException extends RuntimeException{
    public PizzaWithIdNullException() {
        super("Pizza o el id de la Pizza no puede ser null");
    }
}
