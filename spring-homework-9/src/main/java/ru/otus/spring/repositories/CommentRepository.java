package ru.otus.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(Long bookId);

    @Modifying
    @Query("update Comment c set c.text = :text where c.id = :id")
    void updateTextById(@Param("id") Long id, @Param("text") String text);

    void deleteAllByBookId(Long bookId);
}
