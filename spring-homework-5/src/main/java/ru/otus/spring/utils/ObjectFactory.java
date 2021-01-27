package ru.otus.spring.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ObjectFactory {

    public Book createBook(String title, String genreIdOrName, String authors) {
        List<Author> authorsList = createAuthors(authors);
        Genre genre = createGenre(genreIdOrName);

        return Book.builder()
                .title(title)
                .genre(genre)
                .authors(authorsList)
                .build();
    }


    private List<Author> createAuthors(String authors) {
        List<Author> authorsList = new ArrayList<>();
        if (!authors.equals("NONE")) {
            String[] authorsArray = authors.split(";");
            for (String str : authorsArray) {
                String[] data = str.split(",");
                Author author;
                if (data.length == 2) {
                    author = Author.builder()
                            .name(data[0])
                            .surname(data[1])
                            .build();
                } else {
                    author = Author.builder()
                            .id(Long.parseLong(data[0]))
                            .build();
                }
                authorsList.add(author);
            }
        }
        return authorsList;
    }


    private Genre createGenre(String genreIdOrName) {
        try {
            long genreId = Long.parseLong(genreIdOrName);
            return new Genre(genreId);
        } catch (NumberFormatException ex) {
            return new Genre(genreIdOrName);
        }
    }
}
