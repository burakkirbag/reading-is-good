package com.burakkirbag.readingisgood.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItem {

    private String id;

    private String name;

    private BigDecimal unitPrice;

    private Integer quantity;

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(new BigDecimal(quantity));
    }
}
