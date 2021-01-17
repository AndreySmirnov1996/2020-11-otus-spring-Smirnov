package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами ")
@JdbcTest
@Import({BookRepositoryImpl.class, GenreRepositoryImpl.class,
        AuthorRepositoryImpl.class})
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl bookRepository;

    @Test
    void findById() {
        val bookId = 1L;
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        assertTrue(bookOpt.isPresent());

        Book book = bookOpt.get();
        assertEquals(bookId, book.getId());
        //Прочие асерты
    }
}