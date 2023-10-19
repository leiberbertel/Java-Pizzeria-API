package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.PizzaEntity;
import com.leiber.pizza.services.PizzaService;
import com.leiber.pizza.services.dto.UpdatePizzaPriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    @Operation(summary = "Get all pizzas")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements) {
        Page<PizzaEntity> pizzas = this.pizzaService.getAll(page, elements);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/unavailable")
    @Operation(summary = "Get all unavailable pizzas")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<PizzaEntity>> getUnavailablePizza() {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getUnavailable();
        return ResponseEntity.ok(pizzaEntities);
    }

    @GetMapping("/available")
    @Operation(summary = "Get all pizzas")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Page<PizzaEntity>> getAvailablePizza(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "8") int elements,
                                                               @RequestParam(defaultValue = "price") String sortBy,
                                                               @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<PizzaEntity> pizzas = this.pizzaService.getAvailable(page, elements, sortBy, sortDirection);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get pizza by name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found pizza by name")
    })
    public ResponseEntity<PizzaEntity> getByName(
            @Parameter(description = "Pizza name",
                    required = true,
                    example = "La quesua")
            @PathVariable String name) {
        PizzaEntity pizzaEntity = this.pizzaService.getByName(name);
        return ResponseEntity.ok(pizzaEntity);
    }

    @GetMapping("/with/{ingredient}")
    @Operation(summary = "Get pizza with ingredient")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found pizza with ingredient")
    })
    public ResponseEntity<List<PizzaEntity>> getWith(
            @Parameter(description = "Pizza ingredient",
                    required = true,
                    example = "Mozarrella")
            @PathVariable String ingredient) {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getWith(ingredient);
        return ResponseEntity.ok(pizzaEntities);
    }

    @GetMapping("/without/{ingredient}")
    @Operation(summary = "Get pizza without ingredient")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found pizza without ingredient")
    })
    public ResponseEntity<List<PizzaEntity>> getNotWith(
            @Parameter(description = "Ingredient to filter",
                    required = true,
                    example = "Tomato")
            @PathVariable String ingredient) {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getWithOut(ingredient);
        return ResponseEntity.ok(pizzaEntities);
    }

    @GetMapping("/vegan")
    public ResponseEntity<Integer> getCountVeganPizza() {
        Integer count = this.pizzaService.countVeganPizza();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable double price) {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getCheapest(price);
        return ResponseEntity.ok(pizzaEntities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable int id) {
        PizzaEntity pizzaEntity = this.pizzaService.getPizzaById(id);
        return ResponseEntity.ok(pizzaEntity);
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizzaEntity) {
        PizzaEntity pizzaEntityAdded = this.pizzaService.save(pizzaEntity);
        return ResponseEntity.ok(pizzaEntityAdded);
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
        PizzaEntity pizzaEntityUpdate = this.pizzaService.update(pizzaEntity);
        return ResponseEntity.ok(pizzaEntityUpdate);
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
        this.pizzaService.updatePrice(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.pizzaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
