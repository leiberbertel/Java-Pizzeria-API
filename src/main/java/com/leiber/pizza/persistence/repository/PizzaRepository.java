package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link PizzaEntity}.
 * 
 * @author Leiber Bertel
 */
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    /**
     * Encuentra todas las pizzas no disponibles.
     *
     * @return Una lista de entidades de pizzas no disponibles.
     */
    List<PizzaEntity> findAllByAvailableFalse();

    /**
     * Encuentra todas las pizzas disponibles ordenadas por precio.
     *
     * @return Una lista de entidades de pizzas disponibles ordenadas por precio.
     */
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    /**
     * Encuentra la primera pizza disponible por nombre, ignorando mayúsculas y minúsculas.
     *
     * @param name El nombre de la pizza.
     * @return Una entidad de pizza opcional si se encuentra.
     */
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    /**
     * Encuentra las tres pizzas más baratas con precio menor o igual al especificado, ordenadas por precio ascendente.
     *
     * @param price El precio máximo.
     * @return Una lista de las tres entidades de pizzas más baratas.
     */
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    /**
     * Encuentra todas las pizzas disponibles que contienen la descripción especificada, ignorando mayúsculas y minúsculas.
     *
     * @param description La descripción a buscar.
     * @return Una lista de entidades de pizzas que contienen la descripción.
     */
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    /**
     * Encuentra todas las pizzas disponibles que no contienen la descripción especificada, ignorando mayúsculas y minúsculas.
     *
     * @param description La descripción a excluir.
     * @return Una lista de entidades de pizzas que no contienen la descripción.
     */
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    /**
     * Cuenta la cantidad de pizzas veganas disponibles.
     *
     * @return El número de pizzas veganas disponibles.
     */
    int countByVeganTrue();

    /**
     * Actualiza el precio de una pizza especificada por su ID.
     *
     * @param pizzaId El ID de la pizza.
     * @param newPrice El nuevo precio de la pizza.
     */
    @Query(value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :pizzaId", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("pizzaId") int pizzaId, @Param("newPrice") double newPrice);
}
