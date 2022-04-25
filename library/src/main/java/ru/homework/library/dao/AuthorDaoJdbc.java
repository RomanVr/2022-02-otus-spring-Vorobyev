package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.Author;
import ru.homework.library.domain.Book;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return namedJdbc.queryForObject(
                "SELECT id, name, lastName, dateOfBirth, gender " +
                        "FROM Author WHERE id = :id",
                params,
                new AuthorMapper());
    }

    @Override
    public Author getByNameFamily(String name, String lastName) {
        Map<String, Object> params = Map.of("name", name, "lastName", lastName);
        return namedJdbc.queryForObject(
                "SELECT id, name, lastName, dateOfBirth, gender " +
                        "FROM Author " +
                        "WHERE name = :name AND lastName = :lastName",
                params,
                new AuthorMapper());
    }

    @Override
    public long insert(Author author) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", author.getName())
                .addValue("lastName", author.getLastName())
                .addValue("dateOfBirth", author.getDateOfBirth())
                .addValue("gender", author.getGender());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(
                "INSERT INTO Author (name, lastName, dateOfBirth, gender) " +
                        "VALUES (:name, :lastName, :dateOfBirth, :gender)", parameters, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public long update(Author author) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", author.getId())
                .addValue("name", author.getName())
                .addValue("lastName", author.getLastName())
                .addValue("dateOfBirth", author.getDateOfBirth())
                .addValue("gender", author.getGender());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(
                "UPDATE Author SET " +
                        "name = :name, lastName = :lastName, dateOfBirth = :dateOfBirth, gender = :gender " +
                        "WHERE id = :id", parameters, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Map.of("id", id);
        namedJdbc.update("DELETE FROM Author WHERE id = :id", params);
    }

    @Override
    public List<Author> getAll() {
        return namedJdbc.query(
                "SELECT id, name, lastName, dateOfBirth, gender FROM Author",
                new AuthorMapper());
    }

    private class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String lastName = rs.getString("lastName");
            Date dateOfBirth = rs.getDate("dateOfBirth");
            String gender = rs.getString("gender");
            return new Author(id, name, lastName, dateOfBirth, gender);
        }
    }
}
