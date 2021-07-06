package ru.otus.spring.service;

import ru.otus.spring.domain.*;

import java.util.List;

public interface ObjectFactory {

    MongoBook createBookEntity(String title, String genreId, String authors);

    List<MongoAuthor> createAuthors(String authors);

    MongoGenre createGenreEntity(String genreName);
}
