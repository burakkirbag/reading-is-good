package com.burakkirbag.readingisgood.adapters.authentication.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
}