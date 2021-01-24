package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@DisplayName("Репозиторий на основе Jdbc для работы со студентами ")
@JdbcTest
@Import({BookRepositoryImpl.class, GenreRepositoryImpl.class, AuthorRepositoryImpl.class})
class BookRepositoryImplTest {

    @Autowired
    private NamedParameterJdbcOperations jdbc;
    @Autowired
    private BookRepositoryImpl bookRepository;

    @DisplayName("должен находить книгу по id")
    @Test
    void findById() {
//        Genre genre = new Genre(99, "new_genre_name");
//        Author author = new Author(89, "author_name", "author_surname");
//        Book book = new Book(512, "book_title", genre, new ArrayList<>());
//        AuthorBookRelation authorBookRelation = new AuthorBookRelation(author.getId(), book.getId());
//        book.getAuthors().add(author);
//
//        jdbc.update(INSERT_GENRE, getFullSqlParamsGenre(genre));
//        jdbc.update(INSERT_BOOK, getFullSqlParamsBook(book));
//        jdbc.update(INSERT_AUTHOR, getFullSqlParamsAuthor(author));
//        jdbc.update(INSERT_AUTHOR_BOOK_RELATION, getFullSqlParamsAuthorBookRelation(authorBookRelation));
//
//        val bookOpt = bookRepository.findById(book.getId());
//        assertThat(bookOpt).isNotEmpty().get().isEqualTo(book);
    }
}