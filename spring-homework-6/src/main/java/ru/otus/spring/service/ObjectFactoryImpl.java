package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ObjectFactoryImpl implements ObjectFactory {
    @Override
    public Book createBook(long bookId, String title, String cost, long genreId, String genreName, String authors) {
        List<Author> authorsList = createAuthors(authors);
        Genre genre = createGenre(genreId, genreName);

        return Book.builder()
                .id(bookId)
                .title(title)
                .cost(cost)
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
        return authorsList;
    }

    @Override
    public Genre createGenre(long genreId, String genreName) {
        return genreName.equals("NONE") ? new Genre(genreId) : new Genre(genreId, genreName);
    }

    @Override
    public Map<String, String> createBookParamsMap(String bookParams) {
        Map<String, String> bookParamsMap = new HashMap<>();
        String[] paramsArray = bookParams.split(";");
        for (String str : paramsArray) {
            String[] keyValue = str.split("=");
            bookParamsMap.put(keyValue[0], "'" + keyValue[1] + "'");
        }
        return bookParamsMap;
    }
}
