package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import com.burakkirbag.readingisgood.order.model.OrderCustomer;
import com.burakkirbag.readingisgood.order.model.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderCreate implements UseCase {

    private BigDecimal totalPrice;

    private String state;

    private OrderCustomer customer;

    private List<OrderItem> items;
}

