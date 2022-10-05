package com.burakkirbag.readingisgood.adapters.statistic.mongodb.entity;

import com.burakkirbag.readingisgood.common.entity.AbstractEntity;
import com.burakkirbag.readingisgood.statistic.model.Statistic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;

@Getter
@Setter
@Document("statistics")
public class StatisticEntity extends AbstractEntity {

    private String customerId;
    private Integer year;
    private Integer month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedPrice;

    public Statistic toModel() {

        return Statistic.builder()
                .customerId(customerId)
                .year(year)
                .month(month)
                .monthText(new DateFormatSymbols().getMonths()[month].toString())
                .totalOrderCount(totalOrderCount)
                .totalBookCount(totalBookCount)
                .totalPurchasedPrice(totalPurchasedPrice)
                .build();
    }

}
