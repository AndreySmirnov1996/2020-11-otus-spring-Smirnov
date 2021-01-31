package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.BookEntity;
import ru.otus.spring.domain.CommentEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий на основе JPA для работы с книгами должен ")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен находить комментарий по id")
    @Test
    void findByIdTest() {
        val commentId = 7L;

        CommentEntity commentExp = CommentEntity.builder()
                .text("Good book!")
                .book(BookEntity.builder().id(111).build())
                .build();

        val commentOpt = commentRepository.findById(commentId);
        assertThat(commentOpt).isNotEmpty();

        val commentAct = commentOpt.get();
        assertEquals(commentId, commentAct.getId());
        assertEquals(commentExp.getText(), commentAct.getText());
        assertEquals(commentExp.getBook().getId(), commentAct.getBook().getId());

    }

    @DisplayName("должен сохранять комментарий")
    @Test
    void saveTest() {
        CommentEntity comment = createComment("Some bad comment", BookEntity.builder().id(111).build());
        commentRepository.save(comment);

        val bookOpt = commentRepository.findById(comment.getId());
        assertThat(bookOpt).isNotEmpty().get().isEqualTo(comment);
    }

    @DisplayName("должен находить все комментарии по id книги")
    @Test
    void findAllTest() {
        val expectedComments = 1;
        val bookId = 111L;
        List<CommentEntity> comments = commentRepository.findAllByBook_Id(bookId);

        assertEquals(expectedComments, comments.size());
        comments.forEach(comment -> {
                    assertNotNull(comment.getText());
                    assertNotNull(comment.getBook());
                }
        );
    }

    @DisplayName("должен удалять комментарий по id")
    @Test
    void deleteTest() {
        val commentId = 7L;
        commentRepository.deleteById(commentId);
        val commentOpt = commentRepository.findById(commentId);
        assertThat(commentOpt).isEmpty();
    }

    private CommentEntity createComment(String text, BookEntity bookEntity) {
        return CommentEntity.builder()
                .text(text)
                .book(bookEntity)
                .build();
    }
}