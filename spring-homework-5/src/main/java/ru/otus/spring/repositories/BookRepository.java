package ru.otus.spring.repositories;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAllWithAllInfo();

    void updateTitle(long bookId, String newTitle);

    void delete(long bookId);
}
