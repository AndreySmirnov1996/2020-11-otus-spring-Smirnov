package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами ")
@JdbcTest
@Import({BookRepositoryImpl.class, GenreRepositoryImpl.class, AuthorRepositoryImpl.class})
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl bookRepository;

    @DisplayName("должен находить книгу по id")
    @Test
    void findById() {
        Genre genre = new Genre(99, "new_genre_name");
        Author author = new Author(89, "author_name", "author_surname");
        Book book = new Book(512, "book_title", genre, new ArrayList<>());
        book.getAuthors().add(author);
        bookRepository.save(book);

        val bookOpt = bookRepository.findById(book.getId());
        assertThat(bookOpt).isNotEmpty().get().isEqualTo(book);
    }
}