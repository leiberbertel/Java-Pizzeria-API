package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.PizzaEntity;
import com.leiber.pizza.services.PizzaService;
import com.leiber.pizza.services.dto.UpdatePizzaPriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para manejar las operaciones relacionadas con las pizzas.
 * Proporciona endpoints para obtener todas las pizzas, pizzas no disponibles, pizzas disponibles,
 * pizzas por nombre, pizzas con o sin ingredientes específicos, contar pizzas veganas,
 * obtener las pizzas más baratas, y operaciones CRUD básicas.
 * 
 * @author Leiber Bertel
 */

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    /**
     * Constructor para inyectar el servicio de pizzas.
     *
     * @param pizzaService El servicio para gestionar pizzas.
     */
    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    /**
     * Endpoint para obtener todas las pizzas paginadas.
     *
     * @param page El número de la página a obtener.
     * @param elements El número de elementos por página.
     * @return Una respuesta HTTP con la página de entidades de pizzas.
     */
    @GetMapping
    @Operation(summary = "Get all pizzas")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements) {
        Page<PizzaEntity> pizzas = this.pizzaService.getAll(page, elements);
        return ResponseEntity.ok(pizzas);
    }

    /**
     * Endpoint para obtener todas las pizzas no disponibles.
     *
     * @return Una respuesta HTTP con la lista de entidades de pizzas no disponibles.
     */
    @GetMapping("/unavailable")
    @Operation(summary = "Get all unavailable pizzas")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<PizzaEntity>> getUnavailablePizza() {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getUnavailable();
        return ResponseEntity.ok(pizzaEntities);
    }

    /**
     * Endpoint para obtener todas las pizzas disponibles paginadas.
     *
     * @param page El número de la página a obtener.
     * @param elements El número de elementos por página.
     * @param sortBy El campo por el cual ordenar.
     * @param sortDirection La dirección de la ordenación (ASC o DESC).
     * @return Una respuesta HTTP con la página de entidades de pizzas disponibles.
     */
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

    /**
     * Endpoint para obtener una pizza por su nombre.
     *
     * @param name El nombre de la pizza.
     * @return Una respuesta HTTP con la entidad de la pizza.
     */
    @GetMapping("/name/{name}")
    @Operation(summary = "Get pizza by name")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found pizza by name")
    public ResponseEntity<PizzaEntity> getByName(
            @Parameter(description = "Pizza name",
                    required = true,
                    example = "La quesua")
            @PathVariable String name) {
        PizzaEntity pizzaEntity = this.pizzaService.getByName(name);
        return ResponseEntity.ok(pizzaEntity);
    }

    /**
     * Endpoint para obtener pizzas que contienen un ingrediente específico.
     *
     * @param ingredient El ingrediente a buscar.
     * @return Una respuesta HTTP con la lista de entidades de pizzas que contienen el ingrediente.
     */
    @GetMapping("/with/{ingredient}")
    @Operation(summary = "Get pizza with ingredient")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "404", description = "Not found pizza without ingredient")
    public ResponseEntity<List<PizzaEntity>> getWith(
            @Parameter(description = "Pizza ingredient",
                    required = true,
                    example = "Mozarrella")
            @PathVariable String ingredient) {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getWith(ingredient);
        return ResponseEntity.ok(pizzaEntities);
    }

    /**
     * Endpoint para obtener pizzas que no contienen un ingrediente específico.
     *
     * @param ingredient El ingrediente a excluir.
     * @return Una respuesta HTTP con la lista de entidades de pizzas que no contienen el ingrediente.
     */
    @GetMapping("/without/{ingredient}")
    @Operation(summary = "Get pizza without ingredient")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found pizza without ingredient")
    public ResponseEntity<List<PizzaEntity>> getNotWith(
            @Parameter(description = "Ingredient to filter",
                    required = true,
                    example = "Tomato")
            @PathVariable String ingredient) {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getWithOut(ingredient);
        return ResponseEntity.ok(pizzaEntities);
    }

    /**
     * Endpoint para contar la cantidad de pizzas veganas disponibles.
     *
     * @return Una respuesta HTTP con el conteo de pizzas veganas.
     */
    @Operation(summary = "Count vegan pizzas")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/vegan")
    public ResponseEntity<Integer> getCountVeganPizza() {
        Integer count = this.pizzaService.countVeganPizza();
        return ResponseEntity.ok(count);
    }

    /**
     * Endpoint para obtener las pizzas más baratas con precio menor o igual al especificado.
     *
     * @param price El precio máximo.
     * @return Una respuesta HTTP con la lista de las pizzas más baratas.
     */
    @Operation(summary = "Get cheapest pizzas by price")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable double price) {
        List<PizzaEntity> pizzaEntities = this.pizzaService.getCheapest(price);
        return ResponseEntity.ok(pizzaEntities);
    }

    /**
     * Endpoint para obtener una pizza por su ID.
     *
     * @param id El ID de la pizza.
     * @return Una respuesta HTTP con la entidad de la pizza.
     */
    @Operation(summary = "Get pizza by ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found pizza by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable int id) {
        PizzaEntity pizzaEntity = this.pizzaService.getPizzaById(id);
        return ResponseEntity.ok(pizzaEntity);
    }

    /**
     * Endpoint para agregar una nueva pizza.
     *
     * @param pizzaEntity La entidad de la nueva pizza.
     * @return Una respuesta HTTP con la entidad de la pizza agregada.
     */
    @Operation(summary = "Add a new pizza")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizzaEntity) {
        PizzaEntity pizzaEntityAdded = this.pizzaService.save(pizzaEntity);
        return ResponseEntity.ok(pizzaEntityAdded);
    }

    /**
     * Endpoint para actualizar una pizza existente.
     *
     * @param pizzaEntity La entidad de la pizza a actualizar.
     * @return Una respuesta HTTP con la entidad de la pizza actualizada.
     */
    @Operation(summary = "Update an existing pizza")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found pizza to update")
    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity) {
        PizzaEntity pizzaEntityUpdate = this.pizzaService.update(pizzaEntity);
        return ResponseEntity.ok(pizzaEntityUpdate);
    }

    /**
     * Endpoint para actualizar el precio de una pizza.
     *
     * @param dto Los datos de actualización del precio de la pizza.
     * @return Una respuesta HTTP sin contenido.
     */
    @Operation(summary = "Update the price of a pizza")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
        this.pizzaService.updatePrice(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para eliminar una pizza por su ID.
     *
     * @param id El ID de la pizza a eliminar.
     * @return Una respuesta HTTP sin contenido.
     */
    @Operation(summary = "Delete a pizza by ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found pizza to delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.pizzaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
