package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.AuthorBookRelation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorBookRelationRepositoryImpl implements AuthorBookRelationRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void save(AuthorBookRelation authorBookRelation) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("author_id", authorBookRelation.getAuthorId())
                .addValue("book_id", authorBookRelation.getBookId());

        jdbc.update("insert into authors_books (author_id, book_id) values (:author_id, :book_id)", sqlParameterSource);
    }

    @Override
    public List<AuthorBookRelation> findAuthorBookRelationsByBookId(long bookId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("book_id", bookId);

        return jdbc.query("select * from authors_books where book_id=:book_id",
                sqlParameterSource, new AuthorBookRelationRowMapper());
    }


    @Override
    public List<AuthorBookRelation> findAuthorBookRelationsByAuthorId(long authorId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("author_id", authorId);

        return jdbc.query("select * from authors_books where author_id=:author_id",
                sqlParameterSource, new AuthorBookRelationRowMapper());
    }

    @Override
    public void deleteRelationsByBookId(long bookId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("book_id", bookId);

        jdbc.update("delete from authors_books where book_id=:book_id", sqlParameterSource);
    }


    private static class AuthorBookRelationRowMapper implements RowMapper<AuthorBookRelation> {
        @Override
        public AuthorBookRelation mapRow(ResultSet rs, int i) throws SQLException {
            return new AuthorBookRelation(rs.getLong(1), rs.getLong(2));
        }
    }

}
