package com.burakkirbag.readingisgood.customer.service;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.customer.usecase.CustomerCreate;

@DomainComponent
public class CustomerValidator {

    private final CustomerPort customerPort;

    public CustomerValidator(CustomerPort customerPort) {
        this.customerPort = customerPort;
    }

    public void validate(CustomerCreate customerCreate) {
        if (customerPort.exist(customerCreate.getEmail()))
            throw new BusinessException("customerapi.customer.notEmailUnique");
    }
}
