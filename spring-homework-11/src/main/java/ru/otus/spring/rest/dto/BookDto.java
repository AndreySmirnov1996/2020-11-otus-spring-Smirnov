package ru.otus.spring.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
public class BookDto {

    private String id;
    private String title;
    private String genre;
    private String authors;
    private List<String> comments;

    private BookDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        genre = book.getGenre();
        authors = book.getAuthors().stream().map(author -> author.getName() + " " + author.getSurname() + "; ")
                .collect(Collectors.joining());
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book);
    }

    public Book toBook() {
        return Book.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .authors(createAuthors(authors))
                .build();
    }

    private List<Author> createAuthors(String authors) {
        List<Author> authorsList = new ArrayList<>();
        if (!authors.equals("NONE")) {
            String[] authorsArray = authors.split(";");
            for (String authorNames : authorsArray) {
                String[] data = authorNames.trim().split(" ");
                if (data.length == 2) {
                    authorsList.add(Author.builder()
                            .name(data[0])
                            .surname(data[1])
                            .build());
                } else {
                    throw new IllegalArgumentException("\"" + authorNames + "\" can't be parsed as Author");
                }
            }
        }
        return authorsList;
    }
}
