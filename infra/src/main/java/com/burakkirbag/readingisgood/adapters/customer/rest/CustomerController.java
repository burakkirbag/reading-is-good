package com.burakkirbag.readingisgood.adapters.customer.rest;

import com.burakkirbag.readingisgood.adapters.customer.rest.dto.CreateCustomerRequest;
import com.burakkirbag.readingisgood.adapters.customer.rest.dto.CreateCustomerResponse;
import com.burakkirbag.readingisgood.adapters.order.rest.dto.OrderDto;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.common.rest.BaseController;
import com.burakkirbag.readingisgood.common.rest.DataResponse;
import com.burakkirbag.readingisgood.common.rest.Response;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.customer.model.Customer;
import com.burakkirbag.readingisgood.customer.usecase.CustomerCreate;
import com.burakkirbag.readingisgood.customer.usecase.CustomerGetAllOrder;
import com.burakkirbag.readingisgood.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController extends BaseController {

    private final UseCaseHandler<Customer, CustomerCreate> createCustomerUseCaseHandler;

    private final UseCaseHandler<PagedList<Order>, CustomerGetAllOrder> getAllOrderUseCaseHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<CreateCustomerResponse> create(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
        var customer = createCustomerUseCaseHandler.handle(createCustomerRequest.toModel());
        return respond(CreateCustomerResponse.fromModel(customer));
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public Response<DataResponse<OrderDto>> orders(@RequestParam Integer page, @RequestParam Integer size) {
        var orders = getAllOrderUseCaseHandler.handle(CustomerGetAllOrder.builder().customerId(getUser().getId()).size(size).page(page).build());

        return respond(toResponse(orders.getItems()), orders.getPage(), orders.getSize(), orders.getTotalSize());
    }

    private List<OrderDto> toResponse(List<Order> orders) {
        return orders.stream().map(OrderDto::fromModel).collect(Collectors.toList());
    }
}
