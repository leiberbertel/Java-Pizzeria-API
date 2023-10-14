package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.Order;
import com.leiber.pizza.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderServices orderServices;

    @Autowired
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping
    public ResponseEntity<List<Order>> all() {
        return ResponseEntity.ok(this.orderServices.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<Order>> getTodayOrder() {
        return ResponseEntity.ok(this.orderServices.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<Order>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderServices.getOutsideOrders());
    }

    @GetMapping("/onside")
    public ResponseEntity<List<Order>> getOnSideOrders() {
        return ResponseEntity.ok(this.orderServices.getOnSideOrders());
    }
}
