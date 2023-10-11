package com.leiber.pizza.persistence.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {
    private Integer idItem;
    private Integer idOrder;
}
