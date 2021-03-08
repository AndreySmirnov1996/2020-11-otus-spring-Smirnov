package ru.otus.spring.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
public class BookDto {

    private long id;
    private String title;
    private long genreId;
    private String genreName;
    private String authors;
    private List<String> comments;

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
            String[] authorsArray = authors.split(";");
            for (String str : authorsArray) {
                String[] data = str.trim().split(" ");
                Author author;
                switch (data.length) {
                    case 1:
                        author = Author.builder()
                                .id(Long.parseLong(data[0]))
                                .build();
                        break;
                    case 2:
                        author = Author.builder()
                                .name(data[0])
                                .surname(data[1])
                                .build();
                        break;
                    case 3:
                        author = Author.builder()
                                .id(Long.parseLong(data[0]))
                                .name(data[1])
                                .surname(data[2])
                                .build();
                        break;
                    default:
                        throw new IllegalArgumentException("\"" + str + "\" can't be parsed as Author");
                }

                if (author != null) {
                    authorsList.add(author);
                }
            }
        }
        return authorsList;
    }
}
