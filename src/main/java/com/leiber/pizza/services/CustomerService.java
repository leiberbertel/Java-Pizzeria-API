package com.leiber.pizza.services;

import com.leiber.pizza.persistence.entity.CustomerEntity;
import com.leiber.pizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio para gestionar las operaciones relacionadas con los clientes
 * @author Leiber Bertel
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Constructor para inyectar el repositorio de clientes.
     *
     * @param customerRepository El repositorio de clientes.
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Busca un cliente por su número de teléfono.
     *
     * @param phone El número de teléfono del cliente.
     * @return La entidad del cliente correspondiente al número de teléfono, o null si no se encuentra.
     */
    public CustomerEntity findByPhone(String phone) {
        return this.customerRepository.findByPhoneNumberJPQL(phone);
    }
}
