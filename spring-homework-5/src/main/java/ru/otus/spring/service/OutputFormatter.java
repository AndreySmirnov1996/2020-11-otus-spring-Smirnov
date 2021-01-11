package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

public interface OutputFormatter {
    String formatBook(Book book);

    String formatGenre(Genre genre);
}
