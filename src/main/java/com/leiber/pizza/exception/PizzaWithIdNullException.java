package com.leiber.pizza.exception;

/**
 * Excepción lanzada cuando se intenta insertar una pizza en la base de datos con id null o sin cuerpo.
 *
 * @author Leiber Bertel
 */
public class PizzaWithIdNullException extends RuntimeException{

    /**
     * Constructor que crea una nueva excepción con un mensaje detallando que el id o la pizza no puede ser null.
     *
     */
    public PizzaWithIdNullException() {
        super("Pizza o el id de la Pizza no puede ser null");
    }
}
