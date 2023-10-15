package com.leiber.pizza.services;
import com.leiber.pizza.exception.PizzaAlreadyExistsException;
import com.leiber.pizza.exception.PizzaNotFoundException;
import com.leiber.pizza.exception.PizzaWithIdNullException;
import com.leiber.pizza.persistence.entity.Pizza;
import com.leiber.pizza.persistence.repository.PizzaPagSortRepository;
import com.leiber.pizza.persistence.repository.PizzaRepository;
import com.leiber.pizza.services.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PizzaServices {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaServices(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<Pizza> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public List<Pizza> getUnavailable() {
        return this.pizzaRepository.findAllByAvailableFalse();
    }

    public Page<Pizza> getAvailable(int page, int elements, String sortBy, String sortDirection){

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
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

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzaRepository.updatePrice(dto.getPizzaId(), dto.getNewPrice());
    }

    public Pizza getPizzaById(int id) {
        return this.pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }

    public Pizza save(Pizza pizza) {
        if(pizza.getIdPizza() == null || !this.pizzaRepository.existsById(pizza.getIdPizza())) {
            return this.pizzaRepository.save(pizza);
        }

        throw new PizzaAlreadyExistsException(pizza);

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
