package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.OutputFormatter;

import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class BookCrudCommands {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final BookRepository bookRepository;
    private final ObjectFactory objectFactory;

    @ShellMethod(value = "Delete book by id (example: db 1)", key = {"db", "delete book"})
    public void deleteBook(@ShellOption long bookId) {
        bookRepository.delete(bookId);
    }

    @ShellMethod(value = "Update book by id (example: ub 1 title=new_title)", key = {"ub", "update book"})
    public void updateBook(@ShellOption long bookId, @ShellOption String params) {
        Map<String, String> bookParams = objectFactory.createBookParamsMap(params);
        bookRepository.update(bookId, bookParams);
    }

    @ShellMethod(value = "Show all books (example: sab)", key = {"sab", "show all books"})
    public void showAllBooks() {
        bookRepository.findAll().forEach(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    @ShellMethod(value = "Save book (example: sb 3 book_name_3 2 genre_name_2 1;5,Name1,Surname1)",
            key = {"sb", "save book"})
    public void saveBook(@ShellOption long bookId, @ShellOption String title, @ShellOption long genreId,
                         @ShellOption(defaultValue = "NONE") String genreName,
                         @ShellOption(defaultValue = "NONE") String authors) {
        Book book = objectFactory.createBook(bookId, title, genreId, genreName, authors);
        bookRepository.save(book);
    }

}
