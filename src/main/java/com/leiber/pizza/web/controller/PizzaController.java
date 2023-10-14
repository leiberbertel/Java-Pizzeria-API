package com.leiber.pizza.web.controller;

import com.leiber.pizza.errors.PizzaNotFoundException;
import com.leiber.pizza.persistence.entity.Pizza;
import com.leiber.pizza.services.PizzaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaServices pizzaServices;

    @Autowired
    public PizzaController(PizzaServices pizzaServices) {
        this.pizzaServices = pizzaServices;
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> getAll(){
        return ResponseEntity.ok(this.pizzaServices.getAll());
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Pizza>> getUnavailablePizza() {
        return ResponseEntity.ok(this.pizzaServices.getAllUnavailable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable int id) {
        Pizza pizza = this.pizzaServices.getPizzaById(id);
        return ResponseEntity.ok(pizza);
    }

    @PostMapping
    public ResponseEntity<Pizza> add(@RequestBody Pizza pizza) {
        return ResponseEntity.ok(this.pizzaServices.save(pizza));
    }

    @PutMapping
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza) {
        return ResponseEntity.ok(this.pizzaServices.update(pizza));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.pizzaServices.delete(id);
        return ResponseEntity.ok().build();
    }

}
