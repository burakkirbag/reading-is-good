package com.burakkirbag.readingisgood.security;

import com.burakkirbag.readingisgood.authentication.model.AuthenticatedUser;

public interface TokenManager {

    public String generate(AuthenticatedUser user);

    public Boolean validate(String token);

    public String getUsername(String token);

    String getUserId(String token);

    public Boolean isExpired(String token);
}
