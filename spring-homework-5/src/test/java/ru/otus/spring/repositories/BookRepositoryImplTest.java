package ru.otus.spring.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.AuthorBookRelation;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.repositories.AuthorBookRelationRepositoryImpl.getFullSqlParamsAuthorBookRelation;
import static ru.otus.spring.repositories.AuthorRepositoryImpl.getFullSqlParamsAuthor;
import static ru.otus.spring.repositories.BookRepositoryImpl.getFullSqlParamsBook;
import static ru.otus.spring.repositories.GenreRepositoryImpl.getFullSqlParamsGenre;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами ")
@JdbcTest
@Import({BookRepositoryImpl.class, GenreRepositoryImpl.class,
        AuthorRepositoryImpl.class, AuthorBookRelationRepositoryImpl.class})
class BookRepositoryImplTest {

    private static final String INSERT_BOOK = "insert into books (id, title, genre_id) values (:id, :title, :genre_id)";
    private static final String INSERT_GENRE = "insert into genres (`id`, `name`) values (:id, :name)";
    private static final String INSERT_AUTHOR = "insert into authors (id, `name`, surname) values (:id, :name, :surname)";
    private static final String INSERT_AUTHOR_BOOK_RELATION = "insert into authors_books (author_id, book_id) values (:author_id, :book_id);";

    @Autowired
    private NamedParameterJdbcOperations jdbc;
    @Autowired
    private BookRepositoryImpl bookRepository;

    @DisplayName("должен находить книгу по id")
    @Test
    void findById() {
        Genre genre = new Genre(99, "new_genre_name");
        Author author = new Author(89, "author_name", "author_surname");
        Book book = new Book(512, "book_title", genre, new ArrayList<>());
        AuthorBookRelation authorBookRelation = new AuthorBookRelation(author.getId(), book.getId());
        book.getAuthors().add(author);

        jdbc.update(INSERT_GENRE, getFullSqlParamsGenre(genre));
        jdbc.update(INSERT_BOOK, getFullSqlParamsBook(book));
        jdbc.update(INSERT_AUTHOR, getFullSqlParamsAuthor(author));
        jdbc.update(INSERT_AUTHOR_BOOK_RELATION, getFullSqlParamsAuthorBookRelation(authorBookRelation));

        val bookOpt = bookRepository.findById(book.getId());
        assertThat(bookOpt).isNotEmpty().get().isEqualTo(book);
    }
}