package com.burakkirbag.readingisgood.adapters.order.mongodb.repository;

import com.burakkirbag.readingisgood.adapters.order.mongodb.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    Page<OrderEntity> findAllByCustomerId(String customerId, Pageable pageable);

    Page<OrderEntity> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
