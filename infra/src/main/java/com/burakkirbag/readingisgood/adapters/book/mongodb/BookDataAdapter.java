package com.burakkirbag.readingisgood.adapters.book.mongodb;

import com.burakkirbag.readingisgood.adapters.book.mongodb.entity.BookEntity;
import com.burakkirbag.readingisgood.adapters.book.mongodb.repository.BookRepository;
import com.burakkirbag.readingisgood.book.model.Book;
import com.burakkirbag.readingisgood.book.port.BookPort;
import com.burakkirbag.readingisgood.book.usecase.BookCreate;
import com.burakkirbag.readingisgood.book.usecase.BookGetAll;
import com.burakkirbag.readingisgood.book.usecase.BookUpdateStock;
import com.burakkirbag.readingisgood.common.exception.BusinessException;
import com.burakkirbag.readingisgood.common.exception.DataNotFoundException;
import com.burakkirbag.readingisgood.common.model.PagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookDataAdapter implements BookPort {

    private final BookRepository bookRepository;

    @Override
    public Book retrieve(String id) {
        return bookRepository.findById(id)
                .map(BookEntity::toModel)
                .orElseThrow(() -> new DataNotFoundException("bookapi.book.notFound"));
    }

    @Override
    public List<Book> retrieve(List<String> ids) {
        List<Book> books = new ArrayList<Book>();

        bookRepository.findAllById(ids).forEach(bookEntity -> {
            books.add(bookEntity.toModel());
        });

        return books;
    }

    @Override
    public PagedList<Book> getAll(BookGetAll bookGetAll) {
        var page = bookRepository.findAll(Pageable.ofSize(bookGetAll.getSize()).withPage(bookGetAll.getPage() - 1));

        List<Book> books = new ArrayList<Book>();
        page.forEach(bookEntity -> {
            books.add(bookEntity.toModel());
        });

        return new PagedList<Book>(books, bookGetAll.getPage(), bookGetAll.getSize(), page.getTotalElements());
    }

    @Override
    public Book create(BookCreate bookCreate) {
        var bookEntity = new BookEntity();
        bookEntity.setName(bookCreate.getName());
        bookEntity.setAuthor(bookCreate.getAuthor());
        bookEntity.setStock(bookCreate.getStock());
        bookEntity.setPrice(bookCreate.getPrice());
        return bookRepository.save(bookEntity).toModel();
    }

    @Override
    public Book updateStock(BookUpdateStock bookUpdateStock) {
        var bookEntity = bookRepository.findById(bookUpdateStock.getId())
                .orElseThrow(() -> new BusinessException("bookapi.book.notFound"));

        bookEntity.setStock(bookUpdateStock.getStock());
        return bookRepository.save(bookEntity).toModel();
    }

    @Override
    public List<Book> updateStock(List<Book> books) {

        var entities = bookRepository.findAllById(
                books
                        .stream().map((b) -> b.getId())
                        .collect(Collectors.toList()));

        entities.forEach((entity) -> {
            var book = books.stream().filter(b -> b.getId().equals(entity.getId())).findFirst()
                    .orElseThrow(() -> new BusinessException("bookapi.book.notFoundItem"));

            entity.setStock(book.getStock());
        });

        return bookRepository.saveAll(entities).stream().map(x -> x.toModel()).collect(Collectors.toList());
    }
}
