package com.burakkirbag.readingisgood.statistic.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Statistic {
    private String customerId;
    private Integer year;
    private Integer month;
    private String monthText;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedPrice;
}
