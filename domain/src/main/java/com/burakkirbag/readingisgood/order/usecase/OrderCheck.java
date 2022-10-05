package com.burakkirbag.readingisgood.order.usecase;

import com.burakkirbag.readingisgood.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCheck implements UseCase {

    private String id;

    private String customerId;

}
