package com.leiber.pizza.web.controller;

import com.leiber.pizza.exception.PizzaAlreadyExistsException;
import com.leiber.pizza.exception.PizzaNotFoundException;
import com.leiber.pizza.exception.PizzaWithIdNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<String> handlerPizzaNotFound(PizzaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PizzaAlreadyExistsException.class)
    public ResponseEntity<String> handlerPizzaAlreadyExists(PizzaAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PizzaWithIdNullException.class)
    public ResponseEntity<String> handlerPizzaWithIdNull(PizzaWithIdNullException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
