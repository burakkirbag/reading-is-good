package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderGetAllByDateInterval implements UseCase {
    private LocalDate startDate;
    private LocalDate endDate;
    private int size;
    private int page;
}
