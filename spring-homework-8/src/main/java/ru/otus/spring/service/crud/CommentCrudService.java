package ru.otus.spring.service.crud;

public interface CommentCrudService {

    void saveComment(String title, String bookId);

    void showCommentById(String id);

    void showAllCommentsByBookId(String bookId);

    void showAllComments();

    void updateCommentTextById(String id, String text);

    void deleteCommentById(String id);

    void deleteAllCommentsByBookId(String bookId);

}
