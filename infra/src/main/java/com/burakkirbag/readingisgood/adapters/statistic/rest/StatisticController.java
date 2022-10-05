package com.burakkirbag.readingisgood.adapters.statistic.rest;

import com.burakkirbag.readingisgood.adapters.statistic.rest.dto.StatisticDto;
import com.burakkirbag.readingisgood.common.rest.BaseController;
import com.burakkirbag.readingisgood.common.rest.DataResponse;
import com.burakkirbag.readingisgood.common.rest.Response;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.statistic.model.Statistic;
import com.burakkirbag.readingisgood.statistic.usecase.StatisticGetAllByCustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
public class StatisticController extends BaseController {

    private final UseCaseHandler<List<Statistic>, StatisticGetAllByCustomerId> getAllByCustomerIdUseCaseHandler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<DataResponse<StatisticDto>> customerStatistics() {
        var statistics = getAllByCustomerIdUseCaseHandler.handle(StatisticGetAllByCustomerId.builder().customerId(getUser().getId()).build());

        return respond(toResponse(statistics));
    }

    private List<StatisticDto> toResponse(List<Statistic> statistics) {
        return statistics.stream().map(StatisticDto::fromModel).collect(Collectors.toList());
    }
}
