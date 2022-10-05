package com.burakkirbag.readingisgood.statistic.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticGetAllByCustomerId implements UseCase {
    private String customerId;
}
