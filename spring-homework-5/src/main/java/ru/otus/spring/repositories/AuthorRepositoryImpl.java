package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void save(Author author) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("`name`", author.getName())
                .addValue("surname", author.getSurname())
                .addValue("phone", author.getPhone());

        jdbc.update("insert into authors (id, `name`, surname, phone) values (:id, :`name`, :surname, :phone)", sqlParameterSource);
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
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        return Optional.of(jdbc.queryForObject("select * from authors where id=:id",
                sqlParameterSource, new AuthorRowMapper()));
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return new Author(rs.getLong(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), new ArrayList<>());
        }
    }

}
