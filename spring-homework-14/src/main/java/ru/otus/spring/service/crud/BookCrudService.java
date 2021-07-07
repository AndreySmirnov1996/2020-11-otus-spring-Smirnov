package ru.otus.spring.service.crud;

public interface BookCrudService {

    void saveBook(String title, String genreId, String authors);

    void updateBookTitleById(String bookId, String newTitle);

    void deleteBookById(String bookId);

}
