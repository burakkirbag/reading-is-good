package com.burakkirbag.readingisgood.customer.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@DomainComponent
public class CustomerGetByLoginUseCaseHandler implements UseCaseHandler<Customer, CustomerGetByLogin> {

    private final CustomerPort customerPort;

    @Override

    public Customer handle(CustomerGetByLogin useCase) {
        try {
            var customer = customerPort.getByLogin(useCase);
            return customer;
        } catch (Exception e) {
            throw new BusinessException("customerapi.customer.emailOrPasswordIncorrect");
        }
    }
}
