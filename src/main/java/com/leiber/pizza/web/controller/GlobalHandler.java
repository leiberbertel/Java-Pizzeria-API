package com.leiber.pizza.web.controller;

import com.leiber.pizza.exception.PizzaAlreadyExistsException;
import com.leiber.pizza.exception.PizzaNotFoundException;
import com.leiber.pizza.exception.PizzaWithIdNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la aplicación.
 * Proporciona métodos para manejar excepciones específicas y devolver respuestas HTTP adecuadas.
 *
 * @author Leiber Bertel
 */
@RestControllerAdvice
public class GlobalHandler {


    /**
     * Maneja la excepción {@link PizzaNotFoundException}.
     *
     * @param e La excepción lanzada.
     * @return Una respuesta HTTP con el mensaje de la excepción y el estado 404 (Not Found).
     */
    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<String> handlerPizzaNotFound(PizzaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción {@link PizzaAlreadyExistsException}.
     *
     * @param e La excepción lanzada.
     * @return Una respuesta HTTP con el mensaje de la excepción y el estado 409 (Conflict).
     */
    @ExceptionHandler(PizzaAlreadyExistsException.class)
    public ResponseEntity<String> handlerPizzaAlreadyExists(PizzaAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Maneja la excepción {@link PizzaWithIdNullException}.
     *
     * @param e La excepción lanzada.
     * @return Una respuesta HTTP con el mensaje de la excepción y el estado 400 (Bad Request).
     */
    @ExceptionHandler(PizzaWithIdNullException.class)
    public ResponseEntity<String> handlerPizzaWithIdNull(PizzaWithIdNullException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
