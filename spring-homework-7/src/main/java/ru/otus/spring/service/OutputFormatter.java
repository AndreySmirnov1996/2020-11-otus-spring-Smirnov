package ru.otus.spring.service;

import ru.otus.spring.domain.BookEntity;
import ru.otus.spring.domain.CommentEntity;
import ru.otus.spring.domain.GenreEntity;

public interface OutputFormatter {
    String formatBook(BookEntity book);

    String formatGenre(GenreEntity genre);

    String formatComment(CommentEntity comment);
}
