package com.burakkirbag.readingisgood.adapters.order.rest;

import com.burakkirbag.readingisgood.adapters.order.rest.dto.CreateOrderRequest;
import com.burakkirbag.readingisgood.adapters.order.rest.dto.OrderDetailResponse;
import com.burakkirbag.readingisgood.adapters.order.rest.dto.OrderDto;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.common.rest.BaseController;
import com.burakkirbag.readingisgood.common.rest.DataResponse;
import com.burakkirbag.readingisgood.common.rest.Response;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.common.usecase.VoidUseCaseHandler;
import com.burakkirbag.readingisgood.order.model.Order;
import com.burakkirbag.readingisgood.order.usecase.OrderCreate;
import com.burakkirbag.readingisgood.order.usecase.OrderGetAllByDateInterval;
import com.burakkirbag.readingisgood.order.usecase.OrderRetrieve;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController extends BaseController {

    private final VoidUseCaseHandler<OrderCreate> createOrderUseCaseHandler;
    private final UseCaseHandler<Order, OrderRetrieve> retrieveOrderUseCaseHandler;
    private final UseCaseHandler<PagedList<Order>, OrderGetAllByDateInterval> getAllByDateIntervalUseCaseHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        createOrderUseCaseHandler.handle(createOrderRequest.toModel(getUser().getId()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<OrderDetailResponse> detail(@PathVariable String id) {
        var order = retrieveOrderUseCaseHandler.handle(OrderRetrieve.builder().id(id).build());
        return respond(OrderDetailResponse.fromModel(order));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<DataResponse<OrderDto>> listByDateBetween(@RequestParam(name = "startDate")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                   LocalDate startDate,
                                                   @RequestParam(name = "endDate")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                   LocalDate endDate,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer size) {

        var orders = getAllByDateIntervalUseCaseHandler.handle(OrderGetAllByDateInterval.builder().startDate(startDate).endDate(endDate).size(size).page(page).build());

        return respond(toResponse(orders.getItems()), orders.getPage(), orders.getSize(), orders.getTotalSize());
    }

    private List<OrderDto> toResponse(List<Order> orders) {
        return orders.stream().map(OrderDto::fromModel).collect(Collectors.toList());
    }
}
