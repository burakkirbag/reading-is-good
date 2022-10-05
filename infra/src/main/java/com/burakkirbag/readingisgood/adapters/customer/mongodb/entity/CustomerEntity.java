package com.burakkirbag.readingisgood.adapters.customer.mongodb.entity;

import com.burakkirbag.readingisgood.common.entity.AbstractEntity;
import com.burakkirbag.readingisgood.customer.model.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("customers")
public class CustomerEntity extends AbstractEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public Customer toModel() {
        return Customer.builder()
                .id(super.getId())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
    }
}
