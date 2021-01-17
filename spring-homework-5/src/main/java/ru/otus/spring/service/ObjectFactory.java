package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Map;

public interface ObjectFactory {

    Book createBook(long bookId, String title, long genreId, String genreName, String authors);

    List<Author> createAuthors(String authors);

    Genre createGenre(long genreId, String genreName);

    Map<String, String> createBookParamsMap(String bookParams);
}
