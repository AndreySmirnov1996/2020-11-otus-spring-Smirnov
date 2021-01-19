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
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void save(Author author) {
        jdbc.update("insert into authors (id, `name`, surname) values (:id, :name, :surname)",
                getFullSqlParamsAuthor(author));
    }

    @Override
    public void saveAll(List<Author> authorList) {
        List<Map<String, Object>> mapsParamList = new ArrayList<>();
        authorList.stream().filter(author -> author.getName() != null && author.getSurname() != null)
                .forEach(author ->
                        mapsParamList.add(Map.of("id", author.getId(), "name", author.getName(),
                                "surname", author.getSurname())));

        jdbc.batchUpdate("insert into authors (id, `name`, surname) values (:id, :name, :surname)",
                mapsParamList.toArray(new Map[mapsParamList.size()]));
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from authors where id=:id",
                Map.of("id", id), new AuthorRowMapper()));
    }

    private static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return new Author(rs.getLong(1), rs.getString(2), rs.getString(3));
        }
    }

    static SqlParameterSource getFullSqlParamsAuthor(Author author) {
        return new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("name", author.getName())
                .addValue("surname", author.getSurname());
    }

}
