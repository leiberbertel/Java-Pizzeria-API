package com.leiber.pizza.persistence.projection;

import java.time.LocalDateTime;


/**
 * Proyección para resumir la información de una orden.
 * Proporciona solo los datos necesarios para un resumen de la orden.
 *
 * @author Leiber Bertel
 */
public interface OrderSummary {

    /**
     * Obtiene el ID de la orden.
     *
     * @return El ID de la orden.
     */
    Integer getIdOrder();

    /**
     * Obtiene el nombre del cliente que realizó la orden.
     *
     * @return El nombre del cliente.
     */
    String getCustomerName();

    /**
     * Obtiene la fecha y hora en que se realizó la orden.
     *
     * @return La fecha y hora de la orden.
     */
    LocalDateTime getOrderDate();

    /**
     * Obtiene el total de la orden.
     *
     * @return El total de la orden.
     */
    Double getOrderTotal();

    /**
     * Obtiene los nombres de las pizzas incluidas en la orden.
     *
     * @return Los nombres de las pizzas en la orden.
     */
    String getPizzaNames();
}
