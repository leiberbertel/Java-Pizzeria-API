package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<Customer, String> {
    @Query(value = "SELECT c FROM Customer c WHERE c.phoneNumber = :phone", nativeQuery = false)
    Customer findByPhoneNumberJPQL(@Param("phone") String phone);
}
