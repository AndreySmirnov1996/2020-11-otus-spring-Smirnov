package ru.otus.spring.service;

import ru.otus.spring.domain.*;

import java.util.List;

public interface ObjectFactory {

    BookEntity createBookEntity(String title, String genreName, String authors);

    List<AuthorEntity> createAuthors(String authors);

    GenreEntity createGenreEntity(String genreName);
}
