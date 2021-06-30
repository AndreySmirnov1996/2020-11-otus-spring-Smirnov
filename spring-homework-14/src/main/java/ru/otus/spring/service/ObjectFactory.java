package ru.otus.spring.service;

import ru.otus.spring.domain.*;

import java.util.List;

public interface ObjectFactory {

    Book createBookEntity(String title, String genreId, String authors);

    List<Author> createAuthors(String authors);

    Genre createGenreEntity(String genreName);
}
