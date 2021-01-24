package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private static final String INSERT_AUTHOR_QUERY = "insert into authors (`name`, surname) values (?, ?)";

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            return ps;
        }, keyHolder);

        author.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public void saveAll(List<Author> authorList) {
        authorList.forEach(auth -> {
            if (auth.getName() != null && auth.getSurname() != null) {
                save(auth);
            }
        });
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from authors where id=:id",
                Map.of("id", id), new AuthorRowMapper()));
    }

    @Override
    public List<Author> findAllUsed() {
        return jdbc.query("select a.id, a.name, a.surname " +
                "from authors a inner join authors_books ab on a.id = ab.author_id " +
                "group by a.id, a.name " +
                "order by a.id", new AuthorRowMapper());
    }

    @Override
    public List<Author> findAllByBookId(long bookId) {
        return jdbc.query("select a.id, a.name, a.surname from authors a inner join authors_books ab " +
                        "on a.id=ab.author_id where book_id=:book_id", Map.of("book_id", bookId),
                new AuthorRowMapper());
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return new Author(rs.getLong(1), rs.getString(2), rs.getString(3));
        }
    }

    private static SqlParameterSource getFullSqlParamsAuthor(Author author) {
        return new MapSqlParameterSource()
                .addValue("name", author.getName())
                .addValue("surname", author.getSurname());
    }

}
