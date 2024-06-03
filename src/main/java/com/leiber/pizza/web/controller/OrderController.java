package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.OrderEntity;
import com.leiber.pizza.persistence.projection.OrderSummary;
import com.leiber.pizza.services.OrderService;
import com.leiber.pizza.services.dto.RamdonOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para manejar las operaciones relacionadas con los pedidos.
 * Proporciona endpoints para obtener todos los pedidos, pedidos de hoy, pedidos externos, pedidos en el sitio,
 * pedidos de un cliente específico, resúmenes de pedidos y para crear un pedido aleatorio.
 *
 * @author Leiber Bertel
 */
@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    /**
     * Constructor para inyectar el servicio de pedidos.
     *
     * @param orderService El servicio para gestionar pedidos.
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Endpoint para obtener todos los pedidos.
     *
     * @return Una respuesta HTTP con la lista de entidades de pedidos.
     */
    @GetMapping
    public ResponseEntity<List<OrderEntity>> all() {
        List<OrderEntity> orderEntities = this.orderService.getAll();
        return ResponseEntity.ok(orderEntities);
    }

    /**
     * Endpoint para obtener los pedidos realizados hoy.
     *
     * @return Una respuesta HTTP con la lista de entidades de pedidos de hoy.
     */
    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrder() {
        List<OrderEntity> orderEntities = this.orderService.getTodayOrders();
        return ResponseEntity.ok(orderEntities);
    }

    /**
     * Endpoint para obtener los pedidos externos (entrega o para llevar).
     *
     * @return Una respuesta HTTP con la lista de entidades de pedidos externos.
     */
    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        List<OrderEntity> orderEntities = this.orderService.getOutsideOrders();
        return ResponseEntity.ok(orderEntities);
    }

    /**
     * Endpoint para obtener los pedidos realizados en el sitio.
     *
     * @return Una respuesta HTTP con la lista de entidades de pedidos en el sitio.
     */
    @GetMapping("/onside")
    public ResponseEntity<List<OrderEntity>> getOnSideOrders() {
        List<OrderEntity> orderEntities = this.orderService.getOnSideOrders();
        return ResponseEntity.ok(orderEntities);
    }

    /**
     * Endpoint para obtener los pedidos de un cliente específico.
     *
     * @param id El ID del cliente.
     * @return Una respuesta HTTP con la lista de entidades de pedidos del cliente.
     */
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrder(@PathVariable String id) {
        List<OrderEntity> orderEntities = this.orderService.getCustomerOrder(id);
        return ResponseEntity.ok(orderEntities);
    }

    /**
     * Endpoint para obtener el resumen de un pedido específico.
     *
     * @param id El ID del pedido.
     * @return Una respuesta HTTP con el resumen del pedido.
     */
    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable  int id) {
        OrderSummary summary = this.orderService.getSummary(id);
        return ResponseEntity.ok(summary);
    }

    /**
     * Endpoint para crear un pedido aleatorio.
     *
     * @param dto Los datos del pedido aleatorio.
     * @return Una respuesta HTTP con un booleano indicando si el pedido aleatorio fue guardado exitosamente.
     */
    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RamdonOrderDto dto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }
}
