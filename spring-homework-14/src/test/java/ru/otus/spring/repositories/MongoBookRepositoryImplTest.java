package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.AbstractRepositoryTest;
import ru.otus.spring.domain.MongoAuthor;
import ru.otus.spring.domain.MongoBook;
import ru.otus.spring.domain.MongoGenre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с книгами должен ")
class MongoBookRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName(" находить все книги со всей информацией")
    @Test
    void findAllWithAllInfoTest() {
        val expectedBooksSize = 1;
        List<MongoBook> mongoBooks = bookRepository.findAll();
        mongoBooks.forEach(System.out::println);
        assertEquals(expectedBooksSize, mongoBooks.size());
        mongoBooks.forEach(book -> {
                    assertNotNull(book.getTitle());
                    assertNotNull(book.getMongoGenre());
                    assertNotNull(book.getMongoAuthors());
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
        assertEquals("1", actualBook.getMongoGenre().getId());
        assertEquals("Tragedy", actualBook.getMongoGenre().getName());
        assertEquals(1, actualBook.getMongoAuthors().size());
        assertEquals("William", actualBook.getMongoAuthors().get(0).getName());
        assertEquals("Shakespeare", actualBook.getMongoAuthors().get(0).getSurname());
    }

    @DisplayName(" сохранять книгу")
    @Test
    void saveTest() {
        val bookId = "1112";
        MongoBook mongoBook = saveAndReturnBook(bookId);
        val actualBook = bookRepository.findById(mongoBook.getId());
        assertThat(actualBook).isPresent().get().isEqualTo(mongoBook);
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


    private MongoBook saveAndReturnBook(String bookId) {
        MongoAuthor mongoAuthor1 = new MongoAuthor("11", "author_name_1", "author_surname_1");
        MongoAuthor mongoAuthor2 = new MongoAuthor("22", "author_name_2", "author_surname_2");
        MongoGenre mongoGenre = new MongoGenre("11", "genre_1");
        MongoBook mongoBook = createBook(bookId, "book_name_1", new MongoGenre("11", "genre_1"),
                Arrays.asList(mongoAuthor1, mongoAuthor2));
        genreRepository.save(mongoGenre);
        bookRepository.save(mongoBook);
        return mongoBook;
    }

    private MongoBook createBook(String id, String bookTitle, MongoGenre mongoGenre, List<MongoAuthor> mongoAuthors) {
        return MongoBook.builder()
                .id(id)
                .title(bookTitle)
                .mongoGenre(mongoGenre)
                .mongoAuthors(mongoAuthors)
                .build();
    }
}