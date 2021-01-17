package ru.otus.spring.repositories;

import lombok.Data;
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
import ru.otus.spring.repositories.BookRepositoryImpl.AuthorBookRelation.AuthorBookRelationRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.otus.spring.repositories.BookRepositoryImpl.AuthorBookRelation.SELECT_RELATIONS_BY_BOOK_ID;
import static ru.otus.spring.repositories.BookRepositoryImpl.AuthorBookRelation.getFullSqlParamsAuthorBookRelation;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final NamedParameterJdbcOperations jdbc;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void delete(long bookId) {
        SqlParameterSource sqlParameterSourceBook = new MapSqlParameterSource()
                .addValue("id", bookId);
        SqlParameterSource sqlParameterSourceRelation = new MapSqlParameterSource()
                .addValue("book_id", bookId);

        jdbc.update("delete from authors_books where book_id=:book_id", sqlParameterSourceRelation);
        jdbc.update("delete from books where id=:id", sqlParameterSourceBook);
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
        book.getAuthors().forEach(author -> {
            SqlParameterSource sqlParameterSource =
                    getFullSqlParamsAuthorBookRelation(new AuthorBookRelation(author.getId(), book.getId()));
            jdbc.update("insert into authors_books (author_id, book_id) values (:author_id, :book_id)", sqlParameterSource);
        });
    }

    @Override
    public Optional<Book> findById(long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        Book book = jdbc.queryForObject("select b.id, b.title, g.id, g.`name` from books b join genres g on b.genre_id=g.id where b.id=:id",
                sqlParameterSource, new BookRowMapper());

        if (book != null) {
            SqlParameterSource sqlParameterSourceRelation = new MapSqlParameterSource()
                    .addValue("book_id", book.getId());
            var authorBookRelationList = jdbc.query(SELECT_RELATIONS_BY_BOOK_ID, sqlParameterSourceRelation,
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
    public List<Book> findAll() {
        List<Book> books = jdbc.query("select b.id, b.title, g.id, g.`name` from books b join genres g on b.genre_id=g.id order by g.id",
                new BookRowMapper());

        books.forEach(book -> {
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("book_id", book.getId());
            List<AuthorBookRelation> authorBookRelationList =
                    jdbc.query(SELECT_RELATIONS_BY_BOOK_ID, sqlParameterSource, new AuthorBookRelationRowMapper());
            List<Author> authors = new ArrayList<>();
            authorBookRelationList.forEach(relation -> {
                var authorOpt = authorRepository.findById(relation.getAuthorId());
                authorOpt.ifPresent(authors::add);
            });
            book.getAuthors().addAll(authors);
        });


        return books;
    }

    @Override
    public void update(long bookId, Map<String, String> bookParams) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", bookId);

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> param : bookParams.entrySet()) {
            sb.append(param.getKey());
            sb.append("=");
            sb.append(param.getValue());
            sb.append(",");
        }

        sb.deleteCharAt(sb.length() - 1);

        String sql = "update books set " + sb + " where id=:id";

        jdbc.update(sql, sqlParameterSource);
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

    @RequiredArgsConstructor
    @Data
    public static class AuthorBookRelation {
        public static final String SELECT_RELATIONS_BY_BOOK_ID = "select * from authors_books where book_id=:book_id";
        public static final String SELECT_RELATIONS_BY_AUTHOR_ID = "select * from authors_books where author_id=:author_id";

        private final long authorId;
        private final long bookId;

        static SqlParameterSource getFullSqlParamsAuthorBookRelation(AuthorBookRelation authorBookRelation) {
            return new MapSqlParameterSource()
                    .addValue("author_id", authorBookRelation.getAuthorId())
                    .addValue("book_id", authorBookRelation.getBookId());
        }

        public static class AuthorBookRelationRowMapper implements RowMapper<AuthorBookRelation> {
            @Override
            public AuthorBookRelation mapRow(ResultSet rs, int i) throws SQLException {
                return new AuthorBookRelation(rs.getLong(1), rs.getLong(2));
            }
        }
    }

}
