package com.leiber.pizza.persistence.repository;

import com.leiber.pizza.persistence.entity.OrderEntity;
import com.leiber.pizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link OrderEntity}.
 *
 * @author Leiber Bertel
 */
public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    /**
     * Encuentra todas las órdenes con fecha posterior a la especificada.
     *
     * @param date La fecha a partir de la cual buscar órdenes.
     * @return Una lista de entidades de órdenes.
     */
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    /**
     * Encuentra todas las órdenes cuyo método esté en la lista especificada.
     *
     * @param methods La lista de métodos por los cuales filtrar las órdenes.
     * @return Una lista de entidades de órdenes.
     */
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    /**
     * Encuentra todas las órdenes de un cliente específico usando una consulta nativa.
     *
     * @param idCustomer El ID del cliente.
     * @return Una lista de entidades de órdenes del cliente.
     */
    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    /**
     * Encuentra el resumen de una orden específica usando una consulta nativa.
     *
     * @param orderId El ID de la orden.
     * @return El resumen de la orden.
     */
    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
            "       po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames " +
            "FROM pizza_order po " +
            "    INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
            "    INNER JOIN order_item oi on po.id_order = oi.id_order " +
            "    INNER JOIN pizza pi on oi.id_pizza = pi.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.name, po.date, po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);

    /**
    * Procedimiento almacenado para tomar una orden de pizza aleatoria.
    *
    * @param idCustomer El ID del cliente.
    * @param method El método del pedido (e.g., entrega, para llevar).
    * @return True si se tomó la orden, false en caso contrario.
    */
    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);
}
