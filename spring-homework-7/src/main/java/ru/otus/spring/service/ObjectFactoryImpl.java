package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectFactoryImpl implements ObjectFactory {
    @Override
    public BookEntity createBookEntity(String title, String genreName, String authors) {
        List<AuthorEntity> authorsList = createAuthors(authors);
        GenreEntity genre = createGenreEntity(genreName);

        return BookEntity.builder()
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
    public GenreEntity createGenreEntity(String genreIdOrName) {
        GenreEntity genreEntity = new GenreEntity();
        try {
            long genreId = Long.parseLong(genreIdOrName);
            genreEntity.setId(genreId);
            return genreEntity;
        } catch (NumberFormatException ex) {
            genreEntity.setName(genreIdOrName);
        }
        return genreEntity;
    }

    @Override
    public CommentEntity createCommentEntity(String text, long bookId) {
        return CommentEntity.builder()
                .text(text)
                .book(BookEntity.builder().id(bookId).build())
                .build();
    }
}
