package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    @Autowired
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void save(Genre genre) {
        jdbc.update("insert into genres (`id`, `name`) values (:id, :name);", getFullSqlParamsGenre(genre));
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query("select `id`, `name` " +
                "from genres " +
                "order by id", new GenreRowMapper());
    }

    private static class GenreRowMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            return new Genre(rs.getLong(1), rs.getString(2));
        }
    }


    static SqlParameterSource getFullSqlParamsGenre(Genre genre) {
        return new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("name", genre.getName());
    }
}
