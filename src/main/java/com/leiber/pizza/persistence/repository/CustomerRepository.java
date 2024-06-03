package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;


/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link CustomerEntity}.
 *
 * @author Leiber Bertel
 */
public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {

    /**
     * Busca un cliente por su número de teléfono utilizando JPQL.
     *
     * @param phone El número de teléfono del cliente.
     * @return La entidad del cliente correspondiente al número de teléfono.
     */
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone", nativeQuery = false)
    CustomerEntity findByPhoneNumberJPQL(@Param("phone") String phone);
}
