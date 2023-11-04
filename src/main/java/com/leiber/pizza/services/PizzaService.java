package com.leiber.pizza.services;
import com.leiber.pizza.exception.PizzaAlreadyExistsException;
import com.leiber.pizza.exception.PizzaNotFoundException;
import com.leiber.pizza.exception.PizzaWithIdNullException;
import com.leiber.pizza.persistence.entity.PizzaEntity;
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
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public List<PizzaEntity> getUnavailable() {
        return this.pizzaRepository.findAllByAvailableFalse();
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(PizzaNotFoundException::new);
    }

    public List<PizzaEntity> getCheapest(@PathVariable Double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }


    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithOut(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public int countVeganPizza() {
        return this.pizzaRepository.countByVeganTrue();
    }

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzaRepository.updatePrice(dto.getPizzaId(), dto.getNewPrice());
    }

    public PizzaEntity getPizzaById(int id) {
        return this.pizzaRepository.findById(id)
                .orElseThrow(PizzaNotFoundException::new);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        if(pizzaEntity.getIdPizza() == null || !this.pizzaRepository.existsById(pizzaEntity.getIdPizza())) {
            return this.pizzaRepository.save(pizzaEntity);
        }

        throw new PizzaAlreadyExistsException(pizzaEntity);

    }


    public PizzaEntity update(PizzaEntity pizzaEntity) {
        if (pizzaEntity == null || pizzaEntity.getIdPizza() == null) {
            throw new PizzaWithIdNullException();
        }
        if (!this.pizzaRepository.existsById(pizzaEntity.getIdPizza())) {
            throw new PizzaNotFoundException();
        }

        return this.pizzaRepository.save(pizzaEntity);
    }

    public void delete(int id) {
        if (!this.pizzaRepository.existsById(id)){
            throw new PizzaNotFoundException();
        }
        this.pizzaRepository.deleteById(id);
    }
}
