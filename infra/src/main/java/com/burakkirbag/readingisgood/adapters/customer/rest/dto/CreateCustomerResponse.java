package com.burakkirbag.readingisgood.adapters.customer.rest.dto;

import com.burakkirbag.readingisgood.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public static CreateCustomerResponse fromModel(Customer customer) {
        return CreateCustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }
}
