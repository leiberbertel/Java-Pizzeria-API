package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.OrderEntity;
import com.leiber.pizza.persistence.projection.OrderSummary;
import com.leiber.pizza.services.OrderService;
import com.leiber.pizza.services.dto.RamdonOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> all() {
        List<OrderEntity> orderEntities = this.orderService.getAll();
        return ResponseEntity.ok(orderEntities);
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrder() {
        List<OrderEntity> orderEntities = this.orderService.getTodayOrders();
        return ResponseEntity.ok(orderEntities);
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        List<OrderEntity> orderEntities = this.orderService.getOutsideOrders();
        return ResponseEntity.ok(orderEntities);
    }

    @GetMapping("/onside")
    public ResponseEntity<List<OrderEntity>> getOnSideOrders() {
        List<OrderEntity> orderEntities = this.orderService.getOnSideOrders();
        return ResponseEntity.ok(orderEntities);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrder(@PathVariable String id) {
        List<OrderEntity> orderEntities = this.orderService.getCustomerOrder(id);
        return ResponseEntity.ok(orderEntities);
    }
    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable  int id) {
        OrderSummary summary = this.orderService.getSummary(id);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RamdonOrderDto dto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}
