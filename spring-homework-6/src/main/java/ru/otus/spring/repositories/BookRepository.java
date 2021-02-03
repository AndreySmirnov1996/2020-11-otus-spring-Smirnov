package ru.otus.spring.repositories;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateTitleById(long bookId, String newTitle);

    void delete(long bookId);
}
