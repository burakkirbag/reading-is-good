package com.burakkirbag.readingisgood.adapters.statistic.mongodb;

import com.burakkirbag.readingisgood.adapters.statistic.mongodb.entity.StatisticEntity;
import com.burakkirbag.readingisgood.adapters.statistic.mongodb.repository.StatisticRepository;
import com.burakkirbag.readingisgood.statistic.model.Statistic;
import com.burakkirbag.readingisgood.statistic.port.StatisticPort;
import com.burakkirbag.readingisgood.statistic.usecase.StatisticCreate;
import com.burakkirbag.readingisgood.statistic.usecase.StatisticGetAllByCustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticDataAdapter implements StatisticPort {

    private final StatisticRepository statisticRepository;

    @Override
    public Statistic create(StatisticCreate statisticCreate) {

        var statisticEntity = new StatisticEntity();

        var currentEntity = statisticRepository.findOneByCustomerIdAndYearAndMonth(statisticCreate.getCustomerId(), statisticCreate.getYear(), statisticCreate.getMonth());
        if (currentEntity.isPresent()) {
            statisticEntity = currentEntity.get();
            statisticEntity.setTotalOrderCount(statisticEntity.getTotalOrderCount() + statisticCreate.getTotalOrderCount());
            statisticEntity.setTotalBookCount(statisticEntity.getTotalBookCount() + statisticCreate.getTotalBookCount());
            statisticEntity.setTotalPurchasedPrice(statisticEntity.getTotalPurchasedPrice().add(statisticCreate.getTotalPurchasedPrice()));
        } else {
            statisticEntity.setTotalOrderCount(statisticCreate.getTotalOrderCount());
            statisticEntity.setTotalBookCount(statisticCreate.getTotalBookCount());
            statisticEntity.setTotalPurchasedPrice(statisticCreate.getTotalPurchasedPrice());
        }

        statisticEntity.setCustomerId(statisticCreate.getCustomerId());
        statisticEntity.setYear(statisticCreate.getYear());
        statisticEntity.setMonth(statisticCreate.getMonth());

        return statisticRepository.save(statisticEntity).toModel();
    }

    @Override
    public List<Statistic> getAllByCustomerId(StatisticGetAllByCustomerId getAllByCustomerId) {

        var entities = statisticRepository.findAllByCustomerId(getAllByCustomerId.getCustomerId());

        List<Statistic> statistics = new ArrayList<Statistic>();
        entities.forEach(statisticEntity -> {
            statistics.add(statisticEntity.toModel());
        });

        return statistics;
    }
}
