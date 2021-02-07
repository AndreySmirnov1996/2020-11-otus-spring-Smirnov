package ru.otus.spring.service.crud;

public interface BookCrudService {

    void saveBook(String title, String genreName, String authors);

    void showBookById(String id);

    void showAllBooks();

    void updateBookTitleById(String bookId, String newTitle);

    void deleteBookById(String bookId);

}
