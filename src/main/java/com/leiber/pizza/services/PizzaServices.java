package com.leiber.pizza.services;
import com.leiber.pizza.errors.PizzaNotFoundException;
import com.leiber.pizza.persistence.entity.Pizza;
import com.leiber.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaServices {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaServices(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAll() {
        return this.pizzaRepository.findAll();
    }

    public List<Pizza> getAllUnavailable() {
        return this.pizzaRepository.findByAvailableFalse();
    }

    public Pizza getPizzaById(int id) {
        return this.pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }
}
