package com.leiber.pizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leiber.pizza.persistence.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Entidad que representa un elemento perteneciente a una orden en la base de datos.
 * Hereda los campos de auditoría de la clase {@link Auditable}.
 * @author Leiber Bertel
 */
@Entity
@Table(name = "order_item")
@EntityListeners(AuditingEntityListener.class)
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity extends Auditable {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(columnDefinition = "DECIMAL(2,1)")
    private Double quantity;

    @Column(columnDefinition = "DECIMAL(5,2)")
    private Double price;

    @ManyToOne()
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity orderEntity;

    @OneToOne()
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
    private PizzaEntity pizzaEntity;
}
