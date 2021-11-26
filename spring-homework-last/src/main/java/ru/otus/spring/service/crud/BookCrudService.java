package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookCrudService {

    void save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateBookTitleById(long bookId, String newTitle);

    void deleteBookById(long bookId);

}
