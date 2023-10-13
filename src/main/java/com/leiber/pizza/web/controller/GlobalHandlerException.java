package com.leiber.pizza.web.controller;

import com.leiber.pizza.errors.PizzaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(PizzaNotFoundException.class)
    public ResponseEntity<String> handlePizzaNotFound(PizzaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
