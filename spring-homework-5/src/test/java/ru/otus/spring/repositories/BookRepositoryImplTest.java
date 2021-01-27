package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами ")
@JdbcTest
@Import({BookRepositoryImpl.class, GenreRepositoryImpl.class, AuthorRepositoryImpl.class})
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl bookRepository;

    @DisplayName("должен находить книгу по id")
    @Test
    void findByIdTest() {
        val bookId = 111;
        Author author1 = new Author(11, "author_name_1", "author_surname_1");
        Author author2 = new Author(22, "author_name_2", "author_surname_2");
        Book book = createBook(bookId, "book_name_1", new Genre(11, "genre_1"),
                Arrays.asList(author1, author2));

        val bookOpt = bookRepository.findById(bookId);
        assertThat(bookOpt).isNotEmpty().get().isEqualTo(book);
    }

    @DisplayName("должен сохранять книгу")
    @Test
    void saveTest() {
        Book book = createBook(512, "book_title_new", new Genre(99, "new_genre_name"),
                Collections.singletonList(new Author(89, "author_name", "author_surname")));
        bookRepository.save(book);

        val bookOpt = bookRepository.findById(book.getId());
        assertThat(bookOpt).isNotEmpty().get().isEqualTo(book);
    }

    @DisplayName("должен находить все книги со всей информацией")
    @Test
    void findAllWithAllInfoTest() {
        val expectedBooks = 1;
        List<Book> books = bookRepository.findAllWithAllInfo();
        assertEquals(expectedBooks, books.size());
        books.forEach(book -> {
                    assertNotNull(book.getTitle());
                    assertNotNull(book.getGenre());
                    assertNotNull(book.getAuthors());
                }
        );
    }


    private Book createBook(long bookId, String bookTitle, Genre genre, List<Author> authors) {
        return new Book(bookId, bookTitle, genre, authors);
    }
}