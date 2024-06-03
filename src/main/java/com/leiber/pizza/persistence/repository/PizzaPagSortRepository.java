package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

/**
 * Repositorio para gestionar las operaciones de paginación y ordenación de la entidad {@link PizzaEntity}.
 *
 * @author Leiber Bertel
 */
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {

    /**
     * Encuentra todas las pizzas disponibles (available = true) con soporte para paginación.
     *
     * @param pageable La información de paginación y ordenación.
     * @return Una página de entidades de pizzas disponibles.
     */
    Page<PizzaEntity> findByAvailableTrue(Pageable pageable);
}