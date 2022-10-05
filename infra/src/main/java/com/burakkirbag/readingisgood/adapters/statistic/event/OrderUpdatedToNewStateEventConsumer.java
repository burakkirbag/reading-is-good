package com.burakkirbag.readingisgood.adapters.statistic.event;

import com.burakkirbag.readingisgood.common.usecase.BeanAwareUseCasePublisher;
import com.burakkirbag.readingisgood.order.event.OrderUpdatedToNewStateEvent;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.port.OrderPort;
import com.burakkirbag.readingisgood.statistic.usecase.StatisticCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderUpdatedToNewStateEventConsumer extends BeanAwareUseCasePublisher {

    private final OrderPort orderPort;

    @EventListener
    public void consume(OrderUpdatedToNewStateEvent orderUpdatedToNewStateEvent) {
        var order = orderPort.retrieve(orderUpdatedToNewStateEvent.getId());

        publish(buildStatisticCreateUseCaseModel(order));
    }

    private StatisticCreate buildStatisticCreateUseCaseModel(Order order) {

        return StatisticCreate.builder()
                .customerId(order.getCustomer().getId())
                .year(order.getCreatedAt().getYear())
                .month(order.getCreatedAt().getMonth().getValue())
                .totalOrderCount(1)
                .totalBookCount(calculateTotalBookCount(order))
                .totalPurchasedPrice(order.getTotalPrice())
                .build();
    }

    private Integer calculateTotalBookCount(Order order) {
        return order.getItems().stream().mapToInt(x -> x.getQuantity()).sum();
    }
}