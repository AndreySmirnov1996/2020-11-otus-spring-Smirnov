package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectFactoryImpl implements ObjectFactory {
    @Override
    public BookEntity createBookEntity(long bookId, String title, long genreId, String genreName, String authors) {
        List<AuthorEntity> authorsList = createAuthors(authors);
        GenreEntity genre = createGenreEntity(genreId, genreName);

        return BookEntity.builder()
                .id(bookId)
                .title(title)
                .genre(genre)
                .authors(authorsList)
                .build();
    }

    @Override
    public List<AuthorEntity> createAuthors(String authors) {
        List<AuthorEntity> authorsList = new ArrayList<>();
        if (!authors.equals("NONE")) {
            String[] authorsArray = authors.split(";");
            for (String str : authorsArray) {
                String[] data = str.split(",");
                AuthorEntity author;
                if (data.length == 3) {
                    author = AuthorEntity.builder()
                            .id(Long.parseLong(data[0]))
                            .name(data[1])
                            .surname(data[2])
                            .build();
                } else {
                    author = AuthorEntity.builder()
                            .id(Long.parseLong(data[0]))
                            .build();
                }
                authorsList.add(author);
            }
        }
        return authorsList;
    }

    @Override
    public GenreEntity createGenreEntity(long genreId, String genreName) {
        return genreName.equals("NONE") ? new GenreEntity(genreId) : new GenreEntity(genreId, genreName);
    }
}
