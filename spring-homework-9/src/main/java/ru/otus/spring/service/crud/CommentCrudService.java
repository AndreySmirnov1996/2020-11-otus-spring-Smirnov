package ru.otus.spring.service.crud;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentCrudService {

    void saveComment(String title, long bookId);

    void showCommentById(long id);

    List<Comment> findAllCommentsByBookId(long bookId);

    void updateCommentTextById(long id, String text);

    void deleteCommentById(long id);

    void deleteAllCommentsByBookId(long bookId);

}
