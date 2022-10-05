package com.burakkirbag.readingisgood.authentication.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticateWithBasicAuthentication implements UseCase {
    private String username;
    private String password;
}
