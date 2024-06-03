package com.leiber.pizza.exception;

/**
 * Excepción lanzada cuando no se encuentra una pizza en la base de datos.
 *
 * @author Leiber Bertel
 */
public class PizzaNotFoundException extends RuntimeException {

    /**
     * Constructor que crea una nueva excepción con un mensaje detallando que no se ha encontrado la pizza.
     *
     */
    public PizzaNotFoundException() {
        super("No se ha encontrado la pizza");
    }
}
