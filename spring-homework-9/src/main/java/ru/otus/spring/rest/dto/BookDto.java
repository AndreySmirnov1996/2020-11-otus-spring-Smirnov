package ru.otus.spring.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BookDto {

    private long id;
    private String title;
    private long genreId;
    private String genreName;
    private String authors;

    private BookDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        genreId = book.getGenre().getId();
        genreName = book.getGenre().getName();
        authors = book.getAuthors().stream().map(author -> author.getId() + " "
                + author.getName() + " " + author.getSurname() + "; ")
                .collect(Collectors.joining());
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book);
    }

    public Book toBook() {
        return Book.builder()
                .id(id)
                .title(title)
                .genre(new Genre(genreId, genreName))
                .authors(createAuthors(authors))
                .build();
    }

    private List<Author> createAuthors(String authors) {
        List<Author> authorsList = new ArrayList<>();
        if (!authors.equals("NONE")) {
            String[] authorsArray = authors.split("; ");
            for (String str : authorsArray) {
                String[] data = str.split(" ");
                Author author = null;
                if (data.length == 3) {
                    author = Author.builder()
                            .id(Long.parseLong(data[0]))
                            .name(data[1])
                            .surname(data[2])
                            .build();
                } else if (data.length == 1) {
                    author = Author.builder()
                            .id(Long.parseLong(data[0]))
                            .build();
                }
                if (author != null) {
                    authorsList.add(author);
                }
            }
        }
        return authorsList;
    }
}
