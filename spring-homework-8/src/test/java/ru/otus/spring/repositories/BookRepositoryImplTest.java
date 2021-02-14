package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.AbstractRepositoryTest;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с книгами должен ")
class BookRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName(" находить все книги со всей информацией")
    @Test
    void findAllWithAllInfoTest() {
        val expectedBooksSize = 1;
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
        assertEquals(expectedBooksSize, books.size());
        books.forEach(book -> {
                    assertNotNull(book.getTitle());
                    assertNotNull(book.getGenre());
                    assertNotNull(book.getAuthors());
                }
        );
    }

    @DisplayName(" находить книгу по id")
    @Test
    void findByIdTest() {
        val bookId = "1234";
        val actualBook = bookRepository.findById(bookId).orElse(null);
        assertNotNull(actualBook);
        assertEquals(bookId, actualBook.getId());
        assertEquals("Romeo and Juliet", actualBook.getTitle());
        assertEquals("1", actualBook.getGenre().getId());
        assertEquals("Tragedy", actualBook.getGenre().getName());
        assertEquals(1, actualBook.getAuthors().size());
        assertEquals("William", actualBook.getAuthors().get(0).getName());
        assertEquals("Shakespeare", actualBook.getAuthors().get(0).getSurname());
    }

    @DisplayName(" сохранять книгу")
    @Test
    void saveTest() {
        val bookId = "1112";
        Book book = saveAndReturnBook(bookId);
        val actualBook = bookRepository.findById(book.getId());
        assertThat(actualBook).isPresent().get().isEqualTo(book);
        bookRepository.deleteById(bookId);
    }

    @DisplayName(" удалять книгу по id")
    @Test
    void deleteByIdTest() {
        val bookId = "1112";
        saveAndReturnBook(bookId);

        val beforeDeleteBook = bookRepository.findById(bookId).orElse(null);
        assertNotNull(beforeDeleteBook);
        bookRepository.deleteById(bookId);
        val afterDeleteBook = bookRepository.findById(bookId).orElse(null);
        assertNull(afterDeleteBook);
    }


    private Book saveAndReturnBook(String bookId) {
        Author author1 = new Author("11", "author_name_1", "author_surname_1");
        Author author2 = new Author("22", "author_name_2", "author_surname_2");
        Genre genre = new Genre("11", "genre_1");
        Book book = createBook(bookId, "book_name_1", new Genre("11", "genre_1"),
                Arrays.asList(author1, author2));
        genreRepository.save(genre);
        bookRepository.save(book);
        return book;
    }

    private Book createBook(String id, String bookTitle, Genre genre, List<Author> authors) {
        return Book.builder()
                .id(id)
                .title(bookTitle)
                .genre(genre)
                .authors(authors)
                .build();
    }
}