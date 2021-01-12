package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.AuthorBookRelation;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final NamedParameterJdbcOperations jdbc;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final AuthorBookRelationRepository authorBookRelationRepository;

    @Override
    public void delete(long bookId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", bookId);
        authorBookRelationRepository.deleteRelationsByBookId(bookId);
        jdbc.update("delete from books where id=:id", sqlParameterSource);
    }


    @Override
    public void save(Book book) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("title", book.getTitle())
                .addValue("cost", book.getCost())
                .addValue("genre_id", book.getGenre().getId());

        if (book.getGenre().getName() != null) {
            genreRepository.save(book.getGenre());
        }

        if (!book.getAuthors().isEmpty()) {
            authorRepository.saveAll(book.getAuthors());
        }

        jdbc.update("insert into books (id, title, cost, genre_id) values (:id, :title, :cost, :genre_id)",
                sqlParameterSource);
        book.getAuthors().forEach(f ->
                authorBookRelationRepository.save(new AuthorBookRelation(f.getId(), book.getId())));
    }

    @Override
    public Optional<Book> findById(long id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        return Optional.of(jdbc.queryForObject("select * from books b join genres g on b.genre_id=g.id where b.id=:id",
                sqlParameterSource, new BookRowMapper()));
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = jdbc.query("select * from books b join genres g on b.genre_id=g.id order by g.id",
                new BookRowMapper());

        books.forEach(f -> {
            List<AuthorBookRelation> authorBookRelationList =
                    authorBookRelationRepository.findAuthorBookRelationsByBookId(f.getId());

            List<Author> authors = new ArrayList<>();
            authorBookRelationList.forEach(v -> {
                var authorOpt = authorRepository.findById(v.getAuthorId());
                authorOpt.ifPresent(authors::add);
            });

            f.getAuthors().addAll(authors);

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
            return new Book(rs.getLong(1), rs.getString(2), rs.getString(3),
                    new Genre(rs.getLong(4), rs.getString(5)), new ArrayList<>());
        }
    }

}
