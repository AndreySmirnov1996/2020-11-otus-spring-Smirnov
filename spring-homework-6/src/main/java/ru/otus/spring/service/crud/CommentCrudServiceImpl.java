package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public void saveComment(String title, long bookId) {
        Comment comment = objectFactory.createCommentEntity(title, bookId);
        commentRepository.save(comment);
    }

    @Override
    public void showCommentById(long id) {
        commentRepository.findById(id);
    }

    @Override
    public void showAllCommentsByBookId(long bookId) {
        commentRepository.findAllByBookId(bookId)
                .forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    @Override
    public void showAllComments() {
        commentRepository.findAll()
                .forEach(comment -> ioService.printString(outputFormatter.formatComment(comment)));
    }

    @Override
    public void updateCommentTextById(long id, String text) {
        commentRepository.updateTextById(id, text);
    }

    @Override
    public void deleteCommentById(long id) {
        commentRepository.delete(id);
    }

    @Override
    public void deleteAllCommentsByBookId(long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }
}
