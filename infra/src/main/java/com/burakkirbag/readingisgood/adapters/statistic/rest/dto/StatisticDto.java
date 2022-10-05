package com.burakkirbag.readingisgood.adapters.statistic.rest.dto;

import com.burakkirbag.readingisgood.statistic.model.Statistic;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StatisticDto {
    private Integer year;
    private Integer month;
    private String monthText;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedPrice;

    public static StatisticDto fromModel(Statistic statistic) {
        return StatisticDto.builder()
                .year(statistic.getYear())
                .month(statistic.getMonth())
                .monthText(statistic.getMonthText())
                .totalOrderCount(statistic.getTotalOrderCount())
                .totalBookCount(statistic.getTotalBookCount())
                .totalPurchasedPrice(statistic.getTotalPurchasedPrice())
                .build();
    }
}
