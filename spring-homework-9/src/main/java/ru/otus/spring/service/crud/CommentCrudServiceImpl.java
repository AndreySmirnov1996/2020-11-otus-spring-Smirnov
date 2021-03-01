package ru.otus.spring.service.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.service.ObjectFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentCrudServiceImpl implements CommentCrudService {

    private final CommentRepository commentRepository;
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
    public List<Comment> findAllCommentsByBookId(long bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    @Transactional
    @Override
    public void updateCommentTextById(long id, String text) {
        commentRepository.updateTextById(id, text);
    }

    @Transactional
    @Override
    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllCommentsByBookId(long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }
}
