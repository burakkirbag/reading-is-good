package com.burakkirbag.readingisgood.adapters.statistic.mongodb.repository;

import com.burakkirbag.readingisgood.adapters.statistic.mongodb.entity.StatisticEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends MongoRepository<StatisticEntity, String> {

    Optional<StatisticEntity> findOneByCustomerIdAndYearAndMonth(String customerId, Integer year, Integer month);

    List<StatisticEntity> findAllByCustomerId(String customerId);
}
