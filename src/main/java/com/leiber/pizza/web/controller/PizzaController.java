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
        return ResponseEntity.ok(this.pizzaServices.getUnavailable());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Pizza>> getAvailablePizza() {
        return ResponseEntity.ok(this.pizzaServices.getAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pizza> getByName(@PathVariable String name) {
        return ResponseEntity.ok(this.pizzaServices.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<Pizza>> getWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaServices.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<Pizza>> getNotWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(this.pizzaServices.getWithOut(ingredient));
    }

    @GetMapping("/vegan")
    public ResponseEntity<Integer> getCountVeganPizza() {
        return ResponseEntity.ok(this.pizzaServices.countVeganPizza());
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<Pizza>> getCheapestPizzas(@PathVariable double price) {
        return ResponseEntity.ok(this.pizzaServices.getCheapest(price));
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
