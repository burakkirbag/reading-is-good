package com.burakkirbag.readingisgood.security.jwt;

import com.burakkirbag.readingisgood.authentication.port.AuthenticationPort;
import com.burakkirbag.readingisgood.security.TokenManager;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthenticationPort authenticationPort;
    private final TokenManager tokenManager;

    public JwtAuthFilter(AuthenticationPort authenticationPort, TokenManager tokenManager) {
        this.authenticationPort = authenticationPort;
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(7);
        final Boolean isValidToken = tokenManager.validate(token);
        if (!isValidToken) {
            filterChain.doFilter(request, response);
            return;
        }

        final String userName = tokenManager.getUsername(token);
        final var authenticatedUser = authenticationPort.emailAuthentication(userName);
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticatedUser, null, new ArrayList<>());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
