package com.leiber.pizza.exception;

public class PizzaWithIdNullException extends RuntimeException{
    public PizzaWithIdNullException() {
        super("Pizza o el id de la Pizza no puede ser null");
    }
}
