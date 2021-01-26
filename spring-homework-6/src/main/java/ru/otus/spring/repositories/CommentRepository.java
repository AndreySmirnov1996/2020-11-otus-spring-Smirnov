package ru.otus.spring.repositories;

import ru.otus.spring.domain.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    void save(CommentEntity book);

    Optional<CommentEntity> findById(long id);

    List<CommentEntity> findAllByBookId(long bookId);

    List<CommentEntity> findAll();

    void updateText(long bookId, String text);

    void delete(long bookId);
}
