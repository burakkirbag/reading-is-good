package com.burakkirbag.readingisgood.statistic.usecase;

import com.burakkirbag.readingisgood.common.DomainComponent;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.statistic.model.Statistic;
import com.burakkirbag.readingisgood.statistic.port.StatisticPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@DomainComponent
public class StatisticGetAllByCustomerIdUseCaseHandler implements UseCaseHandler<List<Statistic>, StatisticGetAllByCustomerId> {

    private final StatisticPort statisticPort;

    @Override
    public List<Statistic> handle(StatisticGetAllByCustomerId useCase) {
        return statisticPort.getAllByCustomerId(useCase);
    }
}
