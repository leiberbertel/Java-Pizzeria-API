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

/**
 * Servicio para gestionar las operaciones relacionadas con las pizzas.
 * @author Leiber Bertel
 */
@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    /**
     * Constructor para inyectar los repositorios de pizzas.
     *
     * @param pizzaRepository         El repositorio de pizzas.
     * @param pizzaPagSortRepository  El repositorio de paginación y ordenación de pizzas.
     */
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    /**
     * Obtiene todas las pizzas paginadas.
     *
     * @param page      El número de la página.
     * @param elements  El número de elementos por página.
     * @return Una página de entidades de pizzas.
     */
    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    /**
     * Obtiene todas las pizzas no disponibles.
     *
     * @return Una lista de entidades de pizzas no disponibles.
     */
    public List<PizzaEntity> getUnavailable() {
        return this.pizzaRepository.findAllByAvailableFalse();
    }

    /**
     * Obtiene todas las pizzas disponibles, paginadas y ordenadas.
     *
     * @param page          El número de la página.
     * @param elements      El número de elementos por página.
     * @param sortBy        El campo por el cual ordenar.
     * @param sortDirection La dirección de la ordenación (ascendente o descendente).
     * @return Una página de entidades de pizzas disponibles.
     */
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    /**
     * Obtiene una pizza por su nombre.
     *
     * @param name El nombre de la pizza.
     * @return La entidad de la pizza encontrada.
     * @throws PizzaNotFoundException Si no se encuentra la pizza.
     */
    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(PizzaNotFoundException::new);
    }

    /**
     * Obtiene las tres pizzas más baratas con un precio menor o igual al especificado.
     *
     * @param price El precio máximo.
     * @return Una lista de las tres entidades de pizzas más baratas.
     */
    public List<PizzaEntity> getCheapest(@PathVariable Double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    /**
     * Obtiene todas las pizzas disponibles que contienen el ingrediente especificado en su descripción.
     *
     * @param ingredient El ingrediente a buscar.
     * @return Una lista de entidades de pizzas que contienen el ingrediente.
     */
    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    /**
     * Obtiene todas las pizzas disponibles que no contienen el ingrediente especificado en su descripción.
     *
     * @param ingredient El ingrediente a excluir.
     * @return Una lista de entidades de pizzas que no contienen el ingrediente.
     */
    public List<PizzaEntity> getWithOut(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    /**
     * Cuenta la cantidad de pizzas veganas disponibles.
     *
     * @return El número de pizzas veganas disponibles.
     */
    public int countVeganPizza() {
        return this.pizzaRepository.countByVeganTrue();
    }

    /**
     * Actualiza el precio de una pizza.
     * Este método es transaccional.
     *
     * @param dto Los datos de actualización del precio de la pizza.
     */
    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzaRepository.updatePrice(dto.getPizzaId(), dto.getNewPrice());
    }

    /**
     * Obtiene una pizza por su ID.
     *
     * @param id El ID de la pizza.
     * @return La entidad de la pizza encontrada.
     * @throws PizzaNotFoundException Si no se encuentra la pizza.
     */
    public PizzaEntity getPizzaById(int id) {
        return this.pizzaRepository.findById(id)
                .orElseThrow(PizzaNotFoundException::new);
    }

    /**
     * Guarda una nueva pizza.
     *
     * @param pizzaEntity La entidad de la pizza a guardar.
     * @return La entidad de la pizza guardada.
     * @throws PizzaAlreadyExistsException Si la pizza ya existe.
     */
    public PizzaEntity save(PizzaEntity pizzaEntity) {
        if (pizzaEntity.getIdPizza() == null || !this.pizzaRepository.existsById(pizzaEntity.getIdPizza())) {
            return this.pizzaRepository.save(pizzaEntity);
        }
        throw new PizzaAlreadyExistsException(pizzaEntity);
    }

    /**
     * Actualiza una pizza existente.
     *
     * @param pizzaEntity La entidad de la pizza a actualizar.
     * @return La entidad de la pizza actualizada.
     * @throws PizzaWithIdNullException Si el ID de la pizza es nulo.
     * @throws PizzaNotFoundException   Si no se encuentra la pizza.
     */
    public PizzaEntity update(PizzaEntity pizzaEntity) {
        if (pizzaEntity == null || pizzaEntity.getIdPizza() == null) {
            throw new PizzaWithIdNullException();
        }
        if (!this.pizzaRepository.existsById(pizzaEntity.getIdPizza())) {
            throw new PizzaNotFoundException();
        }
        return this.pizzaRepository.save(pizzaEntity);
    }

    /**
     * Elimina una pizza por su ID.
     *
     * @param id El ID de la pizza a eliminar.
     * @throws PizzaNotFoundException Si no se encuentra la pizza.
     */
    public void delete(int id) {
        if (!this.pizzaRepository.existsById(id)) {
            throw new PizzaNotFoundException();
        }
        this.pizzaRepository.deleteById(id);
    }
}
