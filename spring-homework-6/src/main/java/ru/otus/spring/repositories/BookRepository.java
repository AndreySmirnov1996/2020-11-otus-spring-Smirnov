package ru.otus.spring.repositories;

import ru.otus.spring.domain.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    void save(BookEntity book);

    Optional<BookEntity> findById(long id);

    List<BookEntity> findAll();

    void updateTitle(long bookId, String newTitle);

    void delete(long bookId);
}
