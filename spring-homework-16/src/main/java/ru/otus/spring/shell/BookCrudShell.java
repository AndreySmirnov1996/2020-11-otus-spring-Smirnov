package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.crud.BookCrudService;

@ShellComponent
@RequiredArgsConstructor
public class BookCrudShell {

    private final BookCrudService bookCrudService;


    @ShellMethod(value = "Delete book by id (example: db 1)", key = {"db", "delete book"})
    public void deleteBookById(@ShellOption long bookId) {
        bookCrudService.deleteBookById(bookId);
    }

    @ShellMethod(value = "Update title book by id (example: ubt 1 new_title)", key = {"ubt", "update book title"})
    public void updateBookTitleById(@ShellOption long bookId, @ShellOption String newTitle) {
        bookCrudService.updateBookTitleById(bookId, newTitle);
    }

    @ShellMethod(value = "Show all books with all info (example: sab)", key = {"sab", "show all books"})
    public void showAllBooks() {
        bookCrudService.showAllBooks();
    }

    @ShellMethod(value = "Show book by id (example: sbid 1)", key = {"sbid", "show book"})
    public void showBookById(@ShellOption long bookId) {
        bookCrudService.showBookById(bookId);

    }

    //For example:
    //sb book_name_3 genre_name_2 1;Name1,Surname1
    //sb book_name_3 1 1;Name1,Surname1
    //sb book_name_228 1 3
    @ShellMethod(value = "Save book (example: sb book_name_3 genre_name_new 1;Name1,Surname1)",
            key = {"sb", "save book"})
    public void saveBook(@ShellOption String title,
                         @ShellOption String genreName,
                         @ShellOption(defaultValue = "NONE") String authors) {
        bookCrudService.saveBook(title, genreName, authors);
    }

}
