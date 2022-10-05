package com.burakkirbag.readingisgood.adapters.order.mongodb.entity;

import com.burakkirbag.readingisgood.order.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemEntity {

    private String id;

    private String name;

    private BigDecimal unitPrice;

    private Integer quantity;

    public OrderItem toModel() {
        return OrderItem.builder()
                .id(getId())
                .name(name)
                .unitPrice(unitPrice)
                .quantity(quantity)
                .build();
    }
}
