package ru.otus.spring.service.crud;

public interface CommentCrudService {

    void saveComment(String title, long bookId);

    void showCommentById(long id);

    void showAllCommentsByBookId(long bookId);

    void showAllComments();

    void updateCommentTextById(long id, String text);

    void deleteCommentById(long id);

    void deleteAllCommentsByBookId(long bookId);

}
