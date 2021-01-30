package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.AuthorEntity;
import ru.otus.spring.domain.BookEntity;
import ru.otus.spring.domain.GenreEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами ")
@DataJpaTest
@Import({CommentRepositoryImpl.class, GenreRepositoryImpl.class, AuthorRepositoryImpl.class})
class CommentRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl bookRepository;

    @DisplayName("должен находить книгу по id")
    @Test
    void findByIdTest() {
        val bookId = 111;

        AuthorEntity author1 = new AuthorEntity(11, "author_name_1", "author_surname_1");
        AuthorEntity author2 = new AuthorEntity(22, "author_name_2", "author_surname_2");
        BookEntity book = createBook("book_name_1", new GenreEntity(11, "genre_1"),
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
        BookEntity book = createBook("book_title_new", GenreEntity.builder().name("new_genre_name").build(),
                Collections.singletonList(AuthorEntity.builder().name("author_name").surname("author_surname").build()));
        bookRepository.save(book);

        val bookOpt = bookRepository.findById(book.getId());
        assertThat(bookOpt).isNotEmpty().get().isEqualTo(book);
    }

    @DisplayName("должен находить все книги со всей информацией")
    @Test
    void findAllWithAllInfoTest() {
        val expectedBooks = 1;
        List<BookEntity> books = bookRepository.findAll();
        assertEquals(expectedBooks, books.size());
        books.forEach(book -> {
                    assertNotNull(book.getTitle());
                    assertNotNull(book.getGenre());
                    assertNotNull(book.getAuthors());
                }
        );
    }


    private void assertAuthor(AuthorEntity authorExp, AuthorEntity authorAct) {
        assertEquals(authorExp.getId(), authorAct.getId());
        assertEquals(authorExp.getName(), authorAct.getName());
        assertEquals(authorExp.getSurname(), authorAct.getSurname());
    }

    private BookEntity createBook(String bookTitle, GenreEntity genre, List<AuthorEntity> authors) {
        return BookEntity.builder()
                .title(bookTitle)
                .genre(genre)
                .authors(authors)
                .build();
    }
}