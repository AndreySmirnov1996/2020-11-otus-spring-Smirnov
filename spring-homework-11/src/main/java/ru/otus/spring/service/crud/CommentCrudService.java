package ru.otus.spring.service.crud;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Comment;

public interface CommentCrudService {

    void saveComment(Comment comment);

    Mono<Comment> findById(String id);

    Flux<Comment> findAllByBookId(String bookId);

    void updateCommentTextById(String id, String text);

    void deleteCommentById(String id);

    void deleteAllCommentsByBookId(String bookId);

}
