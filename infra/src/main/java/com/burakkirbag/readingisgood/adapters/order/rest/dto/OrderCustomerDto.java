package com.burakkirbag.readingisgood.adapters.order.rest.dto;

import com.burakkirbag.readingisgood.order.model.OrderCustomer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCustomerDto {

    private String id;

    private String name;

    public static OrderCustomerDto fromModel(OrderCustomer customer) {
        return OrderCustomerDto.builder()
                .id(customer.getId())
                .name(String.format("%s %s", customer.getFirstName(), customer.getLastName()))
                .build();
    }
}
