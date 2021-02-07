package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectFactoryImpl implements ObjectFactory {
    @Override
    public Book createBookEntity(String title, String genreName, String authors) {
        List<Author> authorsList = createAuthors(authors);
        Genre genre = createGenreEntity(genreName);

        return Book.builder()
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
                if (data.length == 2) {
                    author = Author.builder()
                            .name(data[0])
                            .surname(data[1])
                            .build();
                } else {
                    author = Author.builder()
                            .id(data[0])
                            .build();
                }
                authorsList.add(author);
            }
        }
        return authorsList;
    }

    @Override
    public Genre createGenreEntity(String genreID) {
        return new Genre(genreID);
    }

    @Override
    public Comment createCommentEntity(String text, String bookId) {


        return Comment.builder()
                .text(text)
                .book(Book.builder().id(bookId).build())
                .build();
    }
}
