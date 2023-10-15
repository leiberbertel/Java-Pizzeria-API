package com.leiber.pizza.services;

import com.leiber.pizza.persistence.entity.Customer;
import com.leiber.pizza.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByPhone(String phone) {
        return this.customerRepository.findByPhoneNumberJPQL(phone);
    }
}
