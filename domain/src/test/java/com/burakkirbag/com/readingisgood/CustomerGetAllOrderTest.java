package com.burakkirbag.com.readingisgood;

import com.burakkirbag.com.readingisgood.adapters.CustomerFakeDataAdapter;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetAllOrder;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetAllOrderUseCaseHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerGetAllOrderTest {

    CustomerGetAllOrderUseCaseHandler customerGetAllOrderUseCaseHandler;

    @BeforeEach
    void setUp() {
        customerGetAllOrderUseCaseHandler = new CustomerGetAllOrderUseCaseHandler(new CustomerFakeDataAdapter());
    }

    @Test
    void should_get_all_orders() {
        // given
        CustomerGetAllOrder customerGetAllOrder = CustomerGetAllOrder.builder()
                .customerId("1")
                .page(1)
                .size(1)
                .build();

        // when
        var orders = customerGetAllOrderUseCaseHandler.handle(customerGetAllOrder);

        // then
        assertThat(orders).isNotNull();
    }
}