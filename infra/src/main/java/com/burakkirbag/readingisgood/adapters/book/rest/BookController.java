package com.burakkirbag.readingisgood.adapters.book.rest;

import com.burakkirbag.readingisgood.adapters.book.rest.dto.BookDto;
import com.burakkirbag.readingisgood.adapters.book.rest.dto.CreateBookRequest;
import com.burakkirbag.readingisgood.adapters.book.rest.dto.CreateBookResponse;
import com.burakkirbag.readingisgood.adapters.book.rest.dto.UpdateStockRequest;
import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.usecase.BookCreate;
import com.burakkirbag.readingisgood.book.usecase.BookGetAll;
import com.burakkirbag.readingisgood.book.usecase.BookRetrieve;
import com.burakkirbag.readingisgood.book.usecase.BookUpdateStock;
import com.burakkirbag.readingisgood.common.model.PagedList;
import com.burakkirbag.readingisgood.common.rest.BaseController;
import com.burakkirbag.readingisgood.common.rest.DataResponse;
import com.burakkirbag.readingisgood.common.rest.Response;
import com.burakkirbag.readingisgood.common.usecase.UseCaseHandler;
import com.burakkirbag.readingisgood.common.usecase.VoidUseCaseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController extends BaseController {

    private final UseCaseHandler<Book, BookCreate> createBookUseCaseHandler;
    private final UseCaseHandler<Book, BookRetrieve> retrieveBookUseCaseHandler;
    private final VoidUseCaseHandler<BookUpdateStock> updateStockVoidUseCaseHandler;
    private final UseCaseHandler<PagedList<Book>, BookGetAll> getAllBookUseCaseHandler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response<CreateBookResponse> create(@RequestBody @Valid CreateBookRequest createBookRequest) {
        var book = createBookUseCaseHandler.handle(createBookRequest.toModel());
        return respond(CreateBookResponse.fromModel(book));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<DataResponse<BookDto>> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        var books = getAllBookUseCaseHandler.handle(BookGetAll.builder().size(size).page(page).build());

        return respond(toResponse(books.getItems()), books.getPage(), books.getSize(), books.getTotalSize());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<BookDto> retrieve(@PathVariable String id) {
        var order = retrieveBookUseCaseHandler.handle(BookRetrieve.builder().id(id).build());
        return respond(BookDto.fromModel(order));
    }

    @PutMapping("/{id}/stock")
    @ResponseStatus(HttpStatus.OK)
    public void updateStock(@PathVariable String id, @RequestBody @Valid UpdateStockRequest updateStockRequest) {
        updateStockVoidUseCaseHandler.handle(updateStockRequest.toModel(id));
    }

    private List<BookDto> toResponse(List<Book> books) {
        return books.stream().map(BookDto::fromModel).collect(Collectors.toList());
    }
}
