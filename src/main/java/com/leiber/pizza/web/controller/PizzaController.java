package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.Pizza;
import com.leiber.pizza.services.PizzaServices;
import com.leiber.pizza.services.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Pizza>> getAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int elements){
        Page<Pizza> pizzas = this.pizzaServices.getAll(page, elements);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Pizza>> getUnavailablePizza() {
        List<Pizza> pizzas = this.pizzaServices.getUnavailable();
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/available")
    public ResponseEntity<Page<Pizza>> getAvailablePizza(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "8") int elements,
                                                         @RequestParam(defaultValue = "price") String sortBy,
                                                         @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<Pizza> pizzas = this.pizzaServices.getAvailable(page, elements, sortBy, sortDirection);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pizza> getByName(@PathVariable String name) {
        Pizza pizza = this.pizzaServices.getByName(name);
        return ResponseEntity.ok(pizza);
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<Pizza>> getWith(@PathVariable String ingredient) {
        List<Pizza> pizzas = this.pizzaServices.getWith(ingredient);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<Pizza>> getNotWith(@PathVariable String ingredient) {
        List<Pizza> pizzas = this.pizzaServices.getWithOut(ingredient);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/vegan")
    public ResponseEntity<Integer> getCountVeganPizza() {
        Integer count = this.pizzaServices.countVeganPizza();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<Pizza>> getCheapestPizzas(@PathVariable double price) {
        List<Pizza> pizzas = this.pizzaServices.getCheapest(price);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable int id) {
        Pizza pizza = this.pizzaServices.getPizzaById(id);
        return ResponseEntity.ok(pizza);
    }

    @PostMapping
    public ResponseEntity<Pizza> add(@RequestBody Pizza pizza) {
        Pizza pizzaAdded = this.pizzaServices.save(pizza);
        return ResponseEntity.ok(pizzaAdded);
    }

    @PutMapping
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza) {
        Pizza pizzaUpdate = this.pizzaServices.update(pizza);
        return ResponseEntity.ok(pizzaUpdate);
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
        this.pizzaServices.updatePrice(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.pizzaServices.delete(id);
        return ResponseEntity.ok().build();
    }

}
