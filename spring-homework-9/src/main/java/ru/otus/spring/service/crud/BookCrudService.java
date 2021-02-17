package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookCrudService {

    void saveBook(String title, String genreName, String authors);

    void showBookById(long id);

    List<Book> findAll();

    void updateBookTitleById(long bookId, String newTitle);

    void deleteBookById(long bookId);

}
