package com.burakkirbag.readingisgood.adapters.order.mongodb.entity;

import com.burakkirbag.readingisgood.order.model.OrderCustomer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCustomerEntity {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    public OrderCustomer toModel() {
        return OrderCustomer.builder()
                .id(getId())
                .firstName(getFirstName())
                .lastName(getLastName())
                .email(getEmail())
                .build();
    }
}
