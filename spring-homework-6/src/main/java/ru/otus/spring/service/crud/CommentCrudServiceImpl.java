package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.ObjectFactory;
import ru.otus.spring.service.OutputFormatter;

@Service
@RequiredArgsConstructor
public class CommentCrudServiceImpl implements CommentCrudService {

    private final CommentRepository commentRepository;
    private final IOService ioService;
    private final OutputFormatter outputFormatter;
    private final ObjectFactory objectFactory;

    @Transactional
    @Override
    public void saveComment(String title, long bookId) {
        Comment comment = objectFactory.createCommentEntity(title, bookId);
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public void showCommentById(long id) {
        commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllCommentsByBookId(long bookId) {
        commentRepository.findAllByBookId(bookId)
                .forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllComments() {
        commentRepository.findAll()
                .forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    @Transactional
    @Override
    public void updateCommentTextById(long id, String text) {
        commentRepository.updateTextById(id, text);
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        commentRepository.delete(id);
    }

    @Transactional
    @Override
    public void deleteAllCommentsByBookId(long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }
}
