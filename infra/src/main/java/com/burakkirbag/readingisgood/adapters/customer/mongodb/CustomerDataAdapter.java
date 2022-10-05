package com.burakkirbag.readingisgood.adapters.customer.mongodb;

import com.burakkirbag.readingisgood.adapters.customer.mongodb.entity.CustomerEntity;
import com.burakkirbag.readingisgood.adapters.customer.mongodb.repository.CustomerRepository;
import com.burakkirbag.readingisgood.common.exception.DataNotFoundException;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.port.CustomerPort;
import com.burakkirbag.readingisgood.customer.usecase.CustomerCreate;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetAllOrder;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetByLogin;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.port.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDataAdapter implements CustomerPort {

    private final CustomerRepository customerRepository;

    private final OrderPort orderPort;

    @Override
    public Customer retrieve(String id) {
        return customerRepository.findById(id)
                .map(CustomerEntity::toModel)
                .orElseThrow(() -> new DataNotFoundException("customerapi.customer.notFound"));
    }

    @Override
    public Customer getByEmail(String email) {
        return customerRepository.findByEmail(email).toModel();
    }

    @Override
    public Customer getByLogin(CustomerGetByLogin customerGetByLogin) {
        return customerRepository.findByEmailAndPassword(customerGetByLogin.getEmail(), customerGetByLogin.getPassword())
                .map(CustomerEntity::toModel)
                .orElseThrow(() -> new DataNotFoundException("customerapi.customer.notFound"));
    }

    @Override
    public Customer create(CustomerCreate customerCreate) {
        var customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerCreate.getFirstName());
        customerEntity.setLastName(customerCreate.getLastName());
        customerEntity.setEmail(customerCreate.getEmail());
        customerEntity.setPassword(customerCreate.getPassword());
        return customerRepository.save(customerEntity).toModel();
    }

    @Override
    public PagedList<Order> getOrders(CustomerGetAllOrder customerGetAllOrder) {
        return orderPort.getAllByCustomerId(customerGetAllOrder.getCustomerId(), customerGetAllOrder.getSize(), customerGetAllOrder.getPage());
    }

    @Override
    public Boolean exist(String email) {
        return customerRepository.existsByEmail(email);
    }
}
