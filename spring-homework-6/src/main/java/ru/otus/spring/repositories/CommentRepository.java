package ru.otus.spring.repositories;

import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    void save(Comment book);

    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(long bookId);

    List<Comment> findAll();

    void updateTextById(long id, String text);

    void delete(long id);

    void deleteAllByBookId(long bookId);
}
