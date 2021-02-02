package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    //void save(Comment book);

    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(long bookId);

    List<Comment> findAll();

    void updateTextById(long id, String text);

    void delete(long id);

    void deleteAllByBookId(long bookId);
}
