package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookEntity;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.OutputFormatter;

@ShellComponent
@RequiredArgsConstructor
public class BookCrudCommands {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final BookRepository bookRepository;
    private final ObjectFactory objectFactory;

    @ShellMethod(value = "Delete book by id (example: db 1)", key = {"db", "delete book"})
    public void deleteBookById(@ShellOption long bookId) {
        bookRepository.delete(bookId);
    }

    @ShellMethod(value = "Update title book by id (example: ubt 1 new_title)", key = {"ubt", "update book title"})
    @Transactional
    public void updateBookTitleById(@ShellOption long bookId, @ShellOption String newTitle) {
        bookRepository.updateTitleById(bookId, newTitle);
    }

    @ShellMethod(value = "Show all books (example: sab)", key = {"sab", "show all books"})
    @Transactional(readOnly = true)
    public void showAllBooks() {
        bookRepository.findAll().forEach(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    @ShellMethod(value = "Show book by id (example: sbid 1)", key = {"sbid", "show book"})
    @Transactional(readOnly = true)
    public void showBookById(@ShellOption long bookId) {
        bookRepository.findById(bookId).ifPresent(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    //For example:
    //sb book_name_3 genre_name_2 1;5,Name1,Surname1
    //sb book_name_3 1 1;5,Name1,Surname1
    //sb book_name_228 1 3
    @ShellMethod(value = "Save book (example: sb book_name_3 genre_name_2 1;5,Name1,Surname1)",
            key = {"sb", "save book"})
    @Transactional
    public void saveBook(@ShellOption String title,
                         @ShellOption(defaultValue = "NONE") String genreName,
                         @ShellOption(defaultValue = "NONE") String authors) {
        BookEntity book = objectFactory.createBookEntity(title, genreName, authors);
        bookRepository.save(book);
    }

}
