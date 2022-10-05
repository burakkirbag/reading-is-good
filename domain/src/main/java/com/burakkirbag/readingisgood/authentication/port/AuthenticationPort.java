package com.burakkirbag.readingisgood.authentication.port;

import com.burakkirbag.readingisgood.authentication.model.AuthenticatedUser;
import com.burakkirbag.readingisgood.authentication.usecase.AuthenticateWithBasicAuthentication;

public interface AuthenticationPort {

    AuthenticatedUser basicAuthentication(AuthenticateWithBasicAuthentication authenticateWithBasicAuthentication);

    AuthenticatedUser emailAuthentication(String email);
}
