package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.ext.AuthorBookRelation;
import ru.otus.spring.repositories.ext.AuthorBookRelation.AuthorBookRelationRowMapper;
import ru.otus.spring.repositories.ext.BookResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static ru.otus.spring.repositories.ext.AuthorBookRelation.SELECT_RELATIONS_BY_BOOK_ID;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final NamedParameterJdbcOperations jdbc;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void delete(long bookId) {
        jdbc.update("delete from authors_books where book_id=:book_id", Map.of("book_id", bookId));
        jdbc.update("delete from books where id=:id", Map.of("id", bookId));
    }


    @Override
    public void save(Book book) {
        if (book.getGenre().getName() != null) {
            genreRepository.save(book.getGenre());
        }
        if (!book.getAuthors().isEmpty()) {
            authorRepository.saveAll(book.getAuthors());
        }
        jdbc.update("insert into books (id, title, genre_id) values (:id, :title, :genre_id)",
                getFullSqlParamsBook(book));

        List<Map<String, Object>> mapsParamList = new ArrayList<>();
        book.getAuthors().forEach(author -> mapsParamList
                .add(Map.of("author_id", author.getId(), "book_id", book.getId())));

        jdbc.batchUpdate("insert into authors_books (author_id, book_id) values (:author_id, :book_id)",
                mapsParamList.toArray(new Map[book.getAuthors().size()]));
    }

    @Override
    public Optional<Book> findById(long id) {
        Book book = jdbc.queryForObject("select b.id, b.title, g.id, g.`name` from books b join genres g on b.genre_id=g.id where b.id=:id",
                Map.of("id", id), new BookRowMapper());

        if (book != null) {
            var authorBookRelationList = jdbc.query(SELECT_RELATIONS_BY_BOOK_ID, Map.of("book_id", book.getId()),
                    new AuthorBookRelationRowMapper());
            List<Author> authors = new ArrayList<>();
            authorBookRelationList.forEach(relation -> {
                        val authorOpt = authorRepository.findById(relation.getAuthorId());
                        authorOpt.ifPresent(authors::add);
                    }
            );
            book.getAuthors().addAll(authors);
            return Optional.of(book);
        }

        return Optional.empty();
    }

    @Override
    public List<Book> findAllWithAllInfo() {
        List<Author> authors = authorRepository.findAllUsed();
        List<AuthorBookRelation> relations = getAllRelations();
        Map<Long, Book> books =
                jdbc.query("select b.id, b.title, b.genre_id, g.`name` " +
                                "from books b left join genres g on b.genre_id = g.id",
                        new BookResultSetExtractor());

        mergeBooksInfo(books, authors, relations);
        return new ArrayList<>(Objects.requireNonNull(books).values());
    }

    @Override
    public void updateTitle(long bookId, String newTitle) {
        String updateTitleSql = "update books set title='" + newTitle + "' where id=:id";
        jdbc.update(updateTitleSql, Map.of("id", bookId));
    }

    private List<AuthorBookRelation> getAllRelations() {
        return jdbc.query("select author_id, book_id from authors_books ab order by author_id, book_id",
                (rs, i) -> new AuthorBookRelation(rs.getLong(1), rs.getLong(2)));
    }

    private void mergeBooksInfo(Map<Long, Book> books, List<Author> authors, List<AuthorBookRelation> relations) {
        Map<Long, Author> authorsMap = authors.stream().collect(Collectors.toMap(Author::getId, c -> c));

        relations.forEach(r -> {
            if (books.containsKey(r.getBookId()) && authorsMap.containsKey(r.getAuthorId())) {
                books.get(r.getBookId()).getAuthors().add(authorsMap.get(r.getAuthorId()));
            }
        });
    }


    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            return new Book(rs.getLong(1), rs.getString(2),
                    new Genre(rs.getLong(3), rs.getString(4)), new ArrayList<>());
        }
    }

    static SqlParameterSource getFullSqlParamsBook(Book book) {
        return new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("genre_id", book.getGenre().getId())
                .addValue("name", book.getGenre().getName());
    }

}
