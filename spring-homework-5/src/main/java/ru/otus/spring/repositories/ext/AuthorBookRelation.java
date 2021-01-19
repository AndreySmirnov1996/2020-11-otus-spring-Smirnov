package ru.otus.spring.repositories.ext;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@RequiredArgsConstructor
public class AuthorBookRelation {
    public static final String SELECT_RELATIONS_BY_BOOK_ID = "select * from authors_books where book_id=:book_id";
    public static final String SELECT_RELATIONS_BY_AUTHOR_ID = "select * from authors_books where author_id=:author_id";

    private final long authorId;
    private final long bookId;

    public static SqlParameterSource getFullSqlParamsAuthorBookRelation(AuthorBookRelation authorBookRelation) {
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
