package com.burakkirbag.readingisgood.adapters.authentication;

import com.burakkirbag.readingisgood.authentication.model.AuthenticatedUser;
import com.burakkirbag.readingisgood.authentication.port.AuthenticationPort;
import com.burakkirbag.readingisgood.authentication.usecase.AuthenticateWithBasicAuthentication;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetByLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationDataAdapter implements AuthenticationPort {

    private final CustomerPort customerPort;

    @Override
    public AuthenticatedUser basicAuthentication(AuthenticateWithBasicAuthentication authenticateWithBasicAuthentication) {
        try {
            var customer = customerPort.getByLogin(CustomerGetByLogin.builder()
                    .email(authenticateWithBasicAuthentication.getUsername())
                    .password(authenticateWithBasicAuthentication.getPassword()).build());

            return buildAuthenticatedUser(customer);
        } catch (Exception ex) {
            throw new BusinessException("authenticationapi.basicAuthentication.notAuthenticated");
        }
    }

    @Override
    public AuthenticatedUser emailAuthentication(String email) {
        try {
            var customer = customerPort.getByEmail(email);

            return buildAuthenticatedUser(customer);
        } catch (Exception ex) {
            throw new BusinessException("authenticationapi.basicAuthentication.notAuthenticated");
        }
    }

    private AuthenticatedUser buildAuthenticatedUser(Customer customer) {
        return AuthenticatedUser.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
    }
}
