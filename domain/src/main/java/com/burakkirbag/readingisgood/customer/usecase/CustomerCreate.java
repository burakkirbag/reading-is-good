package com.burakkirbag.readingisgood.customer.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreate implements UseCase {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
