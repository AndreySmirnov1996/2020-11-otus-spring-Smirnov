package ru.otus.spring.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.AbstractRepositoryTest;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы с комментариями к книгам")
class CommentRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен находить комментарий по id")
    @Test
    void findByIdTest() {
        val commentId = "111";
        val actualCommentOptional = commentRepository.findById(commentId);
        assertThat(actualCommentOptional).isNotEmpty();
        val actualComment = actualCommentOptional.get();
        assertEquals(commentId, actualComment.getId());
        assertEquals("The best tragedy!", actualComment.getText());
        assertEquals("1234", actualComment.getBook().getId());
    }

    @DisplayName("должен сохранять комментарий")
    @Test
    void saveTest() {
        val commentId = "777";
        Comment expectedComment = createComment(commentId, "Some bad comment", Book.builder().id("1234").build());
        commentRepository.save(expectedComment);

        val actualCommentOptional = commentRepository.findById(commentId);
        assertThat(actualCommentOptional).isNotEmpty();
        val actualComment = actualCommentOptional.get();

        assertEquals(commentId, actualComment.getId());
        assertEquals(expectedComment.getText(), actualComment.getText());
        assertEquals(expectedComment.getBook().getId(), actualComment.getBook().getId());
        commentRepository.deleteById(commentId);
    }

    @DisplayName("должен находить все комментарии по id книги")
    @Test
    void findAllTest() {
        val expectedCommentsSize = 2;
        val bookId = "1234";
        List<Comment> comments = commentRepository.findAllByBookId(bookId);

        assertEquals(expectedCommentsSize, comments.size());
        comments.forEach(comment -> {
                    assertNotNull(comment.getId());
                    assertNotNull(comment.getText());
                    assertNotNull(comment.getBook());
                    assertEquals(bookId, comment.getBook().getId());
                }
        );
    }

    @DisplayName("удалять комментарий по id")
    @Test
    void deleteTest() {
        val commentId = "7";
        Comment expectedComment = createComment(commentId, "Some bad comment", Book.builder().id("1234").build());
        commentRepository.save(expectedComment);
        commentRepository.deleteById(commentId);
        val actualCommentOptional = commentRepository.findById(commentId);

        assertThat(actualCommentOptional).isEmpty();
    }

    private Comment createComment(String id, String text, Book book) {
        return Comment.builder()
                .id(id)
                .text(text)
                .book(book)
                .build();
    }
}