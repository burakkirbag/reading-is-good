package com.burakkirbag.readingisgood.authentication.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticatedUser {

    private String id;

    private String firstName;

    private String lastName;

    private String email;
}
