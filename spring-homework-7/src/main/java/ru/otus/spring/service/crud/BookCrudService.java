package ru.otus.spring.service.crud;

public interface BookCrudService {

    void saveBook(String title, String genreName, String authors);

    void showBookById(long id);

    void showAllBooks();

    void updateBookTitleById(long bookId, String newTitle);

    void deleteBookById(long bookId);

}
