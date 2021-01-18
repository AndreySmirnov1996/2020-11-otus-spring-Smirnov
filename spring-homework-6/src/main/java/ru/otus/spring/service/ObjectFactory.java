package ru.otus.spring.service;

import ru.otus.spring.domain.*;

import java.util.List;

public interface ObjectFactory {

    BookEntity createBookEntity(long bookId, String title, long genreId, String genreName, String authors);

    List<AuthorEntity> createAuthors(String authors);

    GenreEntity createGenreEntity(long genreId, String genreName);
}
