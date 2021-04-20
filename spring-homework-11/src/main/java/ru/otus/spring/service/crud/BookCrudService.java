package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookCrudService {

    void save(Book book);

    Optional<Book> findById(String id);

    List<Book> findAll();

    void updateBookTitleById(String bookId, String newTitle);

    void deleteBookById(String bookId);
}
