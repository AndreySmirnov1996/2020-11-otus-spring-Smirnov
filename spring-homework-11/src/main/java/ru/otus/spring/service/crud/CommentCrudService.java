package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentCrudService {

    void saveComment(Comment comment);

    Optional<Comment> findById(String id);

    List<Comment> findAllByBookId(String bookId);

    void updateCommentTextById(String id, String text);

    void deleteCommentById(String id);

    void deleteAllCommentsByBookId(String bookId);

}
