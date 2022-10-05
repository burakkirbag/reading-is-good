package com.burakkirbag.readingisgood.adapters.order.mongodb;

import com.burakkirbag.readingisgood.adapters.order.mongodb.entity.OrderCustomerEntity;
import com.burakkirbag.readingisgood.adapters.order.mongodb.entity.OrderEntity;
import com.burakkirbag.readingisgood.adapters.order.mongodb.entity.OrderItemEntity;
import com.burakkirbag.readingisgood.adapters.order.mongodb.repository.OrderRepository;
import com.burakkirbag.readingisgood.common.exception.DataNotFoundException;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.model.OrderCustomer;
import com.burakkirbag.readingisgood.order.model.OrderItem;
import com.burakkirbag.readingisgood.order.port.OrderPort;
import com.burakkirbag.readingisgood.order.usecase.OrderCreate;
import com.burakkirbag.readingisgood.order.usecase.OrderGetAllByDateInterval;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDataAdapter implements OrderPort {

    private final OrderRepository orderRepository;

    @Override
    public Order retrieve(String id) {
        return orderRepository.findById(id)
                .map(OrderEntity::toModel)
                .orElseThrow(() -> new DataNotFoundException("orderapi.order.notFound"));
    }

    @Override
    public Order create(OrderCreate orderCreate) {
        var orderEntity = new OrderEntity();
        orderEntity.setTotalPrice(orderCreate.getTotalPrice());
        orderEntity.setState(orderCreate.getState());
        setCustomer(orderEntity, orderCreate.getCustomer());
        setItems(orderEntity, orderCreate.getItems());

        return orderRepository.save(orderEntity).toModel();
    }

    @Override
    public PagedList<Order> getAllByCustomerId(String customerId, Integer size, Integer page) {
        var pagedOrders = orderRepository.findAllByCustomerId(customerId, Pageable.ofSize(size).withPage(page - 1));

        List<Order> orders = new ArrayList<Order>();
        pagedOrders.forEach(orderEntity -> {
            orders.add(orderEntity.toModel());
        });

        return new PagedList<Order>(orders, page, size, pagedOrders.getTotalElements());
    }

    @Override
    public PagedList<Order> getAllByDateInterval(OrderGetAllByDateInterval orderGetAllByDateInterval) {
        var pagedOrders = orderRepository.findAllByCreatedAtBetween(
                LocalDateTime.of(orderGetAllByDateInterval.getStartDate(), LocalTime.MIN),
                LocalDateTime.of(orderGetAllByDateInterval.getEndDate(), LocalTime.MIN),
                Pageable.ofSize(orderGetAllByDateInterval.getSize()).withPage(orderGetAllByDateInterval.getPage() - 1));

        List<Order> orders = new ArrayList<Order>();
        pagedOrders.forEach(orderEntity -> {
            orders.add(orderEntity.toModel());
        });

        return new PagedList<Order>(orders, orderGetAllByDateInterval.getPage(), orderGetAllByDateInterval.getSize(), pagedOrders.getTotalElements());
    }

    @Override
    public void updateState(String id, String state) {
        var orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("orderapi.order.notFound"));

        orderEntity.setState(state);
        orderRepository.save(orderEntity);
    }


    private void setCustomer(OrderEntity orderEntity, OrderCustomer customer) {
        var orderCustomerEntity = new OrderCustomerEntity();
        orderCustomerEntity.setId(customer.getId());
        orderCustomerEntity.setFirstName(customer.getFirstName());
        orderCustomerEntity.setLastName(customer.getLastName());
        orderCustomerEntity.setEmail(customer.getEmail());
        orderEntity.setCustomer(orderCustomerEntity);
    }

    private void setItems(OrderEntity orderEntity, List<OrderItem> items) {
        var itemEntities = new ArrayList<OrderItemEntity>();
        items.stream().forEach(item -> {
            var itemEntity = new OrderItemEntity();
            itemEntity.setId(item.getId());
            itemEntity.setName(item.getName());
            itemEntity.setUnitPrice(item.getUnitPrice());
            itemEntity.setQuantity(item.getQuantity());
            itemEntities.add(itemEntity);
        });

        orderEntity.setItems(itemEntities);
    }
}
