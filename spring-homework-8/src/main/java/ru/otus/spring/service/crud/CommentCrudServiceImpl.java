package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.OutputFormatter;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentCrudServiceImpl implements CommentCrudService {

    private final CommentRepository commentRepository;
    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final ObjectFactory objectFactory;

    @Override
    public void saveComment(String title, String bookId) {
        Comment comment = objectFactory.createCommentEntity(title, bookId);
        commentRepository.save(comment);
    }

    @Override
    public void showCommentById(String id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            ioService.printString(outputFormatter.formatComment(commentOptional.get()));
        } else {
            log.warn("Comment with id = {} doesn't exist", id);
        }
    }

    @Override
    public void showAllCommentsByBookId(String bookId) {
        commentRepository.findAllByBookId(bookId)
                .forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    @Override
    public void showAllComments() {
        commentRepository.findAll()
                .forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    @Override
    public void updateCommentTextById(String id, String text) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setText(text);
            commentRepository.save(comment);
        } else {
            log.warn("Comment with id = {} doesn't exist", id);
        }
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
