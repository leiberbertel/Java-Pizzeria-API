package com.leiber.pizza.web.controller;

import com.leiber.pizza.persistence.entity.CustomerEntity;
import com.leiber.pizza.persistence.entity.OrderEntity;
import com.leiber.pizza.services.CustomerService;
import com.leiber.pizza.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller para manejar las operaciones relacionadas con los clientes.
 * Proporciona endpoints para obtener clientes por teléfono y para obtener pedidos de clientes.
 * 
 * @author Leiber Bertel
 */

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    /**
     * Constructor para inyectar las dependencias de CustomerService y OrderService.
     *
     * @param customerService El servicio para gestionar clientes.
     * @param orderService El servicio para gestionar pedidos.
     */
    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    /**
     * Endpoint para obtener un cliente por su número de teléfono.
     *
     * @param phone El número de teléfono del cliente.
     * @return Una respuesta HTTP con la entidad del cliente.
     */
    @Operation(summary = "Get a customer by phone number")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "No customer with that phone number has been found")
    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone) {
        CustomerEntity customerEntity = this.customerService.findByPhone(phone);
        return ResponseEntity.ok(customerEntity);
    }

    /**
     * Endpoint para obtener los pedidos de un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return Una respuesta HTTP con la lista de entidades de pedidos del cliente.
     */
    @Operation(summary = "Gets a customer's orders by customer id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
        List<OrderEntity> orderEntities = this.orderService.getCustomerOrder(id);
        return ResponseEntity.ok(orderEntities);
    }

}
