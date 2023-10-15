package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.Pizza;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Query(value =
            "UPDATE pizza " +
                    "SET price = :newPrice " +
                    "WHERE id_pizza = :pizzaId", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("pizzaId") int pizzaId, @Param("newPrice") double newPrice);

}
