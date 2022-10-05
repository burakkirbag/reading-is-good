package com.burakkirbag.readingisgood.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.burakkirbag.readingisgood.authentication.model.AuthenticatedUser;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.security.TokenManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenManager implements TokenManager {
    private final Environment environment;
    private final CustomerPort customerPort;
    private final Algorithm hmac512;
    private final JWTVerifier jwtVerifier;

    public JwtTokenManager(Environment environment, CustomerPort customerPort) {
        this.environment = environment;
        this.hmac512 = Algorithm.HMAC512(environment.getProperty("token.secret"));
        this.jwtVerifier = JWT.require(this.hmac512).build();
        this.customerPort = customerPort;
    }

    @Override
    public String generate(AuthenticatedUser user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + +Long.parseLong(environment.getProperty("token.expiration_time"))))
                .sign(this.hmac512);
    }

    @Override
    public Boolean validate(String token) {

        try {
            jwtVerifier.verify(token).getSubject();
            return true;
        } catch (final JWTVerificationException verificationEx) {
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        var decoded = getDecodedToken(token);
        return decoded.getSubject();
    }

    @Override
    public String getUserId(String token) {
        var decoded = getDecodedToken(token);
        return decoded.getClaim("id").asString();
    }

    @Override
    public Boolean isExpired(String token) {
        var decoded = getDecodedToken(token);
        return decoded.getExpiresAt().after(new Date(System.currentTimeMillis()));
    }

    private DecodedJWT getDecodedToken(String token) {
        return jwtVerifier.verify(token);
    }
}
