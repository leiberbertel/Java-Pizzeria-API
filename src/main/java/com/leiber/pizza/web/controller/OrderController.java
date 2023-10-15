package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.Order;
import com.leiber.pizza.persistence.projection.OrderSummary;
import com.leiber.pizza.services.OrderServices;
import com.leiber.pizza.services.dto.RamdonOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<Order> orders = this.orderServices.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/today")
    public ResponseEntity<List<Order>> getTodayOrder() {
        List<Order> orders = this.orderServices.getTodayOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/outside")
    public ResponseEntity<List<Order>> getOutsideOrders() {
        List<Order> orders = this.orderServices.getOutsideOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/onside")
    public ResponseEntity<List<Order>> getOnSideOrders() {
        List<Order> orders = this.orderServices.getOnSideOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Order>> getCustomerOrder(@PathVariable String id) {
        List<Order> orders = this.orderServices.getCustomerOrder(id);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable  int id) {
        OrderSummary summary = this.orderServices.getSummary(id);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RamdonOrderDto dto) {
        return ResponseEntity.ok(this.orderServices.saveRandomOrder(dto));
    }
}
