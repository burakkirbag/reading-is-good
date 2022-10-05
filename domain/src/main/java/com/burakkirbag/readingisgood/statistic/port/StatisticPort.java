package com.burakkirbag.readingisgood.statistic.port;

import com.burakkirbag.readingisgood.statistic.model.Statistic;
import com.burakkirbag.readingisgood.statistic.usecase.StatisticCreate;
import com.burakkirbag.readingisgood.statistic.usecase.StatisticGetAllByCustomerId;

import java.util.List;

public interface StatisticPort {

    Statistic create(StatisticCreate statisticCreate);

    List<Statistic> getAllByCustomerId(StatisticGetAllByCustomerId getAllByCustomerId);
}
