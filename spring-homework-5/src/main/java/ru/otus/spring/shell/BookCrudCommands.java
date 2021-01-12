package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.OutputFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ShellComponent
@RequiredArgsConstructor
public class BookCrudCommands {

    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final BookRepository bookRepository;

    @ShellMethod(value = "Delete book by id (example: db 1)", key = {"db", "delete book"})
    public void deleteBook(@ShellOption long bookId) {
        bookRepository.delete(bookId);
    }

    @ShellMethod(value = "Update book by id (example: ub 1 cost=123.12;title=new_title)", key = {"ub", "update book"})
    public void updateBook(@ShellOption long bookId, @ShellOption String params) {
        Map<String, String> bookParams = new HashMap<>();
        String[] paramsArray = params.split(";");
        for (String str : paramsArray) {
            String[] keyValue = str.split("=");
            bookParams.put(keyValue[0], "'" + keyValue[1] + "'");
        }
        bookRepository.update(bookId, bookParams);
    }

    @ShellMethod(value = "Show all books (example: sab)", key = {"sab", "show all books"})
    public void showAllBooks() {
        bookRepository.findAll().forEach(book -> ioService.printString(outputFormatter.formatBook(book)));
    }

    @ShellMethod(value = "Save book (example: sb 3 book_name_3 3.33 2 genre_name_2 1;5,Name1,Surname1,8802131233)",
            key = {"sb", "save book"})
    public void saveBook(@ShellOption long bookId, @ShellOption String title, @ShellOption String cost,
                         @ShellOption long genreId, @ShellOption(defaultValue = "NONE") String genreName,
                         @ShellOption(defaultValue = "NONE") String authors) {
        List<Author> authorsList = new ArrayList<>();
        if (!authors.equals("NONE")) {
            String[] authorsArray = authors.split(";");
            for (String str : authorsArray) {
                String[] data = str.split(",");
                Author author;
                if (data.length == 4) {
                    author = Author.builder()
                            .id(Long.parseLong(data[0]))
                            .name(data[1])
                            .surname(data[2])
                            .phone(data[3])
                            .build();
                } else {
                    author = Author.builder()
                            .id(Long.parseLong(data[0]))
                            .build();
                }
                authorsList.add(author);
            }
        }
        Genre genre = genreName.equals("NONE") ? new Genre(genreId) : new Genre(genreId, genreName);
        Book book = Book.builder()
                .id(bookId)
                .title(title)
                .cost(cost)
                .genre(genre)
                .authors(authorsList)
                .build();
        bookRepository.save(book);
    }

}
