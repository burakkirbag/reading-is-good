package com.burakkirbag.readingisgood.statistic.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StatisticCreate implements UseCase {
    private String customerId;
    private Integer year;
    private Integer month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedPrice;
}
