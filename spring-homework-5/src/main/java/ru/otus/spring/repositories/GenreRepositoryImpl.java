package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private static final String INSERT_GENRE_QUERY = "insert into genres (`name`) values (?)";


    @Autowired
    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_GENRE_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, genre.getName());
            return ps;
        }, keyHolder);

        genre.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
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
                .addValue("name", genre.getName());
    }
}
