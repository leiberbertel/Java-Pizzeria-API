package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.CustomerEntity;
import com.leiber.pizza.persistence.entity.OrderEntity;
import com.leiber.pizza.services.CustomerService;
import com.leiber.pizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
        CustomerEntity customerEntity = this.customerService.findByPhone(phone);
        return ResponseEntity.ok(customerEntity);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
        List<OrderEntity> orderEntities = this.orderService.getCustomerOrder(id);
        return ResponseEntity.ok(orderEntities);
    }

}
