package com.burakkirbag.readingisgood.adapters.book.mongodb.repository;

import com.burakkirbag.readingisgood.adapters.book.mongodb.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, String> {
}
