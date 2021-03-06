package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DisplayName("Репозиторий на основе JPA для работы с книгами должен ")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("должен находить книгу по id")
    @Test
    void findByIdTest() {
        val bookId = 111L;

        Author author1 = new Author(11, "author_name_1", "author_surname_1");
        Author author2 = new Author(22, "author_name_2", "author_surname_2");
        Book book = createBook("book_name_1", new Genre(11, "genre_1"),
                Arrays.asList(author1, author2));

        val bookOpt = bookRepository.findById(bookId);
        assertThat(bookOpt).isNotEmpty();

        val bookActual = bookOpt.get();
        assertEquals(book.getTitle(), bookActual.getTitle());
        assertEquals(book.getGenre().getName(), bookActual.getGenre().getName());
        assertEquals(book.getAuthors().size(), bookActual.getAuthors().size());
        assertAuthor(book.getAuthors().get(0), author1);
        assertAuthor(book.getAuthors().get(1), author2);
    }

    @DisplayName("должен сохранять книгу")
    @Test
    void saveTest() {
        Book book = createBook("book_title_new", Genre.builder().name("new_genre_name").build(),
                Collections.singletonList(Author.builder().name("author_name").surname("author_surname").build()));
        bookRepository.save(book);

        val bookOpt = bookRepository.findById(book.getId());
        assertThat(bookOpt).isNotEmpty().get().isEqualTo(book);
    }

    @DisplayName("должен находить все книги со всей информацией")
    @Test
    void findAllWithAllInfoTest() {
        val expectedBooks = 1;
        List<Book> books = bookRepository.findAll();
        assertEquals(expectedBooks, books.size());
        books.forEach(book -> {
                    assertNotNull(book.getTitle());
                    assertNotNull(book.getGenre());
                    assertNotNull(book.getAuthors());
                }
        );
    }


    private void assertAuthor(Author authorExp, Author authorAct) {
        assertEquals(authorExp.getId(), authorAct.getId());
        assertEquals(authorExp.getName(), authorAct.getName());
        assertEquals(authorExp.getSurname(), authorAct.getSurname());
    }

    private Book createBook(String bookTitle, Genre genre, List<Author> authors) {
        return Book.builder()
                .title(bookTitle)
                .genre(genre)
                .authors(authors)
                .build();
    }
}