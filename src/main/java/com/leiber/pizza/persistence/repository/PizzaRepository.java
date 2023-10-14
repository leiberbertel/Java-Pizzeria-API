package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<Pizza, Integer> {
    List<Pizza> findAllByAvailableFalse();
    List<Pizza> findAllByAvailableTrueOrderByPrice();
    Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    int countByVeganTrue();
}
