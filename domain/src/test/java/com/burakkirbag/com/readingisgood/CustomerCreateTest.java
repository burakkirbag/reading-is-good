package com.burakkirbag.com.readingisgood;

import com.burakkirbag.com.readingisgood.adapters.CustomerFakeDataAdapter;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.service.CustomerValidator;
import com.burakkirbag.readingisgood.customer.usecase.CustomerCreate;
import com.burakkirbag.readingisgood.customer.usecase.CustomerCreateUseCaseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerCreateTest {

    CustomerCreateUseCaseHandler customerCreateUseCaseHandler;

    @BeforeEach
    void setUp() {
        customerCreateUseCaseHandler = new CustomerCreateUseCaseHandler(new CustomerFakeDataAdapter(), new CustomerValidator(new CustomerFakeDataAdapter()));
    }

    @Test
    void should_create_customer() {
        // given
        CustomerCreate customerCreate = CustomerCreate.builder()
                .firstName("firstname")
                .lastName("lastname")
                .email("unique")
                .password("password")
                .build();

        // when
        var customer = customerCreateUseCaseHandler.handle(customerCreate);

        // then
        assertThat(customer).isNotNull()
                .returns("1", Customer::getId)
                .returns("firstname", Customer::getFirstName)
                .returns("lastname", Customer::getLastName)
                .returns("unique", Customer::getEmail)
                .returns("password", Customer::getPassword);
    }

    @Test
    void should_create_customer_not_unique_email() {

        // given
        CustomerCreate customerCreate = CustomerCreate.builder()
                .firstName("firstname")
                .lastName("lastname")
                .email("email")
                .password("password")
                .build();


        var exception = assertThrows(BusinessException.class, () -> {
            var customer = customerCreateUseCaseHandler.handle(customerCreate);
        });

        String expectedKey = "customerapi.customer.notEmailUnique";
        String actualKey = exception.getKey();

        assertTrue(expectedKey.contains(actualKey));

    }
}
