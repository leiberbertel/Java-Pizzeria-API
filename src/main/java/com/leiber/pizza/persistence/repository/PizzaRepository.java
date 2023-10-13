package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
    List<Pizza> findByAvailableFalse();
}
