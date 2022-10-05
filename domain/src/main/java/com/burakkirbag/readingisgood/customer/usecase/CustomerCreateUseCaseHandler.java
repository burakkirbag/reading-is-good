package com.burakkirbag.readingisgood.customer.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.customer.service.CustomerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@DomainComponent
public class CustomerCreateUseCaseHandler implements UseCaseHandler<Customer, CustomerCreate> {

    private final CustomerPort customerPort;
    private final CustomerValidator customerValidator;

    @Override
    public Customer handle(CustomerCreate useCase) {

        customerValidator.validate(useCase);

        try {
            var customer = customerPort.create(useCase);
            return customer;
        } catch (Exception e) {
            throw new BusinessException("customerapi.customer.notCreated");
        }
    }
}
