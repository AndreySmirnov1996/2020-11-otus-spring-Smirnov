package ru.otus.spring.rest.dto;

import lombok.Data;
import ru.otus.spring.domain.Book;

import java.util.stream.Collectors;

@Data
public class BookDto {

    private final long id;
    private final String title;
    private final String genreName;
    private final String authors;

    private BookDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        genreName = book.getGenre().getName();
        authors = book.getAuthors().stream().map(author -> author.getName() + " " + author.getSurname() + "; ")
                .collect(Collectors.joining());
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book);
    }
}
