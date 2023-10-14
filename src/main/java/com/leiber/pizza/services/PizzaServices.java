package com.leiber.pizza.services;
import com.leiber.pizza.errors.PizzaAlreadyExistsException;
import com.leiber.pizza.errors.PizzaNotFoundException;
import com.leiber.pizza.errors.PizzaWithIdNullException;
import com.leiber.pizza.persistence.entity.Pizza;
import com.leiber.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    public List<Pizza> getUnavailable() {
        return this.pizzaRepository.findAllByAvailableFalse();
    }

    public List<Pizza> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public Pizza getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("No existe la pizza con el nombre: " + name));
    }

    public List<Pizza> getCheapest(@PathVariable Double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }


    public List<Pizza> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<Pizza> getWithOut(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public int countVeganPizza() {
        return this.pizzaRepository.countByVeganTrue();
    }

    public Pizza getPizzaById(int id) {
        return this.pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }

    public Pizza save(Pizza pizza) {
        if (pizza == null || pizza.getIdPizza() == null) {
            throw new PizzaWithIdNullException();
        }

        if(this.pizzaRepository.existsById(pizza.getIdPizza())) {
            throw new PizzaAlreadyExistsException(pizza);
        }

        return this.pizzaRepository.save(pizza);
    }


    public Pizza update(Pizza pizza) {
        if (pizza == null || pizza.getIdPizza() == null) {
            throw new PizzaWithIdNullException();
        }
        if (!this.pizzaRepository.existsById(pizza.getIdPizza())) {
            throw new PizzaNotFoundException(pizza.getIdPizza());
        }

        return this.pizzaRepository.save(pizza);
    }

    public void delete(int id) {
        if (!this.pizzaRepository.existsById(id)){
            throw new PizzaNotFoundException(id);
        }
        this.pizzaRepository.deleteById(id);
    }
}
