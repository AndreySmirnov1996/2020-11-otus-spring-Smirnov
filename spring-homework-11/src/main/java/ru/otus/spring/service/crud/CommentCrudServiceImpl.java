package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentCrudServiceImpl implements CommentCrudService {

    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Mono<Comment> findById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public Flux<Comment> findAllByBookId(String bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Override
    public void updateCommentTextById(String id, String text) {
        commentRepository.findById(id).subscribe( comment -> {
            comment.setText(text);
            commentRepository.save(comment);
        }, error -> log.warn("Comment with id = {} doesn't exist or error : {}", id, error.getMessage()));
    }

    @Override
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteAllCommentsByBookId(String bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }
}
