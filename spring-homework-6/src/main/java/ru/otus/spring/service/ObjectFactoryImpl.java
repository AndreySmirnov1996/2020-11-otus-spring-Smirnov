package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectFactoryImpl implements ObjectFactory {
    @Override
    public Book createBook(long bookId, String title, long genreId, String genreName, String authors) {
        List<Author> authorsList = createAuthors(authors);
        Genre genre = createGenre(genreId, genreName);

        return Book.builder()
                .id(bookId)
                .title(title)
                .genre(genre)
                .authors(authorsList)
                .build();
    }

    @Override
    public List<Author> createAuthors(String authors) {
        List<Author> authorsList = new ArrayList<>();
        if (!authors.equals("NONE")) {
            String[] authorsArray = authors.split(";");
            for (String str : authorsArray) {
                String[] data = str.split(",");
                Author author;
                if (data.length == 3) {
                    author = Author.builder()
                            .id(Long.parseLong(data[0]))
                            .name(data[1])
                            .surname(data[2])
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

    @Override
    public Genre createGenre(long genreId, String genreName) {
        return genreName.equals("NONE") ? new Genre(genreId) : new Genre(genreId, genreName);
    }
}