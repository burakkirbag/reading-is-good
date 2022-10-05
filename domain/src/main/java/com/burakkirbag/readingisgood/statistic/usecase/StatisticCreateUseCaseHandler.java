package com.burakkirbag.readingisgood.statistic.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.usecase.ObservableUseCasePublisher;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.statistic.model.Statistic;
import com.burakkirbag.readingisgood.statistic.port.StatisticPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
public class StatisticCreateUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<Statistic, StatisticCreate> {

    private final StatisticPort statisticPort;

    public StatisticCreateUseCaseHandler(StatisticPort statisticPort) {
        this.statisticPort = statisticPort;
        register(StatisticCreate.class, this);
    }

    @Override
    public Statistic handle(StatisticCreate useCase) {
        return statisticPort.create(useCase);
    }
}