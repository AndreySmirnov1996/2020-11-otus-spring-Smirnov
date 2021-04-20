package ru.otus.spring.service.crud;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Book;

public interface BookCrudService {

    void save(Book book);

    Mono<Book> findById(String id);

    Flux<Book> findAll();
//
//    void updateBookTitleById(String bookId, String newTitle);
//
//    void deleteBookById(String bookId);
}
