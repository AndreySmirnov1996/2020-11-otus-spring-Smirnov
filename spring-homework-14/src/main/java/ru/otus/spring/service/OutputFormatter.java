package ru.otus.spring.service;

import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.domain.MongoGenre;

public interface OutputFormatter {
    String formatBook(MongoBook mongoBook);

    String formatGenre(MongoGenre mongoGenre);

}
