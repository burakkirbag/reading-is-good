package com.burakkirbag.readingisgood.adapters.authentication.rest;

import com.burakkirbag.readingisgood.adapters.authentication.rest.dto.AuthenticationRequest;
import com.burakkirbag.readingisgood.adapters.authentication.rest.dto.AuthenticationResponse;
import com.burakkirbag.readingisgood.authentication.model.AuthenticatedUser;
import com.burakkirbag.readingisgood.authentication.usecase.AuthenticateWithBasicAuthentication;
import com.burakkirbag.readingisgood.common.rest.BaseController;
import com.burakkirbag.readingisgood.common.rest.Response;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController extends BaseController {

    private final UseCaseHandler<AuthenticatedUser, AuthenticateWithBasicAuthentication> authenticateWithBasicAuthenticationUseCaseHandler;
    private final TokenManager tokenManager;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        var user = authenticateWithBasicAuthenticationUseCaseHandler.handle(buildAuthenticateWithBasicAuthentication(authenticationRequest));
        var token = tokenManager.generate(user);

        return respond(AuthenticationResponse.builder().token(token).build());
    }

    private AuthenticateWithBasicAuthentication buildAuthenticateWithBasicAuthentication(AuthenticationRequest authenticationRequest) {
        return AuthenticateWithBasicAuthentication.builder()
                .username(authenticationRequest.getUsername())
                .password(authenticationRequest.getPassword())
                .build();
    }
}
