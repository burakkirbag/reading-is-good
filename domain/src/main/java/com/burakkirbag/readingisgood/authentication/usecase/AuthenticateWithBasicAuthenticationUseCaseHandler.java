package com.burakkirbag.readingisgood.authentication.usecase;

import com.burakkirbag.readingisgood.authentication.model.AuthenticatedUser;
import com.burakkirbag.readingisgood.authentication.port.AuthenticationPort;
import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@DomainComponent
public class AuthenticateWithBasicAuthenticationUseCaseHandler implements UseCaseHandler<AuthenticatedUser, AuthenticateWithBasicAuthentication> {

    private final AuthenticationPort authenticationPort;

    @Override
    public AuthenticatedUser handle(AuthenticateWithBasicAuthentication useCase) {
        return authenticationPort.basicAuthentication(useCase);
    }
}
