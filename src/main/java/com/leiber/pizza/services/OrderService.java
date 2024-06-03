package com.leiber.pizza.services;

import com.leiber.pizza.persistence.entity.OrderEntity;
import com.leiber.pizza.persistence.projection.OrderSummary;
import com.leiber.pizza.persistence.repository.OrderRepository;
import com.leiber.pizza.services.dto.RamdonOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con los pedidos.
 * @author Leiber Bertel
 */
@Service
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    private final OrderRepository orderRepository;

    /**
     * Constructor para inyectar el repositorio de pedidos.
     *
     * @param orderRepository El repositorio de pedidos.
     */
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Obtiene todos los pedidos.
     *
     * @return Una lista de todas las entidades de pedidos.
     */
    public List<OrderEntity> getAll() {
        return this.orderRepository.findAll();
    }

    /**
     * Obtiene los pedidos realizados hoy.
     *
     * @return Una lista de entidades de pedidos realizados hoy.
     */
    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    /**
     * Obtiene los pedidos realizados fuera del sitio (entrega o para llevar).
     *
     * @return Una lista de entidades de pedidos realizados fuera del sitio.
     */
    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    /**
     * Obtiene los pedidos realizados en el sitio.
     *
     * @return Una lista de entidades de pedidos realizados en el sitio.
     */
    public List<OrderEntity> getOnSideOrders() {
        List<String> methods = List.of(ON_SITE);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    /**
     * Obtiene los pedidos de un cliente específico.
     * Este método solo puede ser accedido por usuarios con el rol de ADMIN.
     *
     * @param idCustomer El ID del cliente.
     * @return Una lista de entidades de pedidos del cliente.
     */
    @Secured("ROLE_ADMIN")
    public List<OrderEntity> getCustomerOrder(String idCustomer) {
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    /**
     * Obtiene el resumen de un pedido específico.
     *
     * @param orderId El ID del pedido.
     * @return El resumen del pedido.
     */
    public OrderSummary getSummary(int orderId) {
        return this.orderRepository.findSummary(orderId);
    }

    /**
     * Guarda un pedido aleatorio en la base de datos.
     * Este método es transaccional.
     *
     * @param randomOrderDto Los datos del pedido aleatorio.
     * @return True si el pedido fue guardado exitosamente, false en caso contrario.
     */
    @Transactional
    public boolean saveRandomOrder(RamdonOrderDto randomOrderDto) {
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}