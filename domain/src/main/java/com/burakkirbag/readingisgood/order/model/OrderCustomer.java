package com.burakkirbag.readingisgood.order.model;

import com.burakkirbag.readingisgood.customer.model.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCustomer {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    public static OrderCustomer from(Customer customer) {
        return OrderCustomer.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }
}
