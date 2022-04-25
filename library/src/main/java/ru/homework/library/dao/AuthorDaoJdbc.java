package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.Author;
import ru.homework.library.domain.Book;

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
        Map<Long, Author> authors = namedJdbc.query(
                "SELECT a.id, a.name, a.lastName, a.dateOfBirth, a.gender, b.id book_id, b.bookTitle, b.preview " +
                        "FROM Author a LEFT JOIN Book b on a.id = b.author_id WHERE a.id = :id",
                params,
                new AuthorResultSetExtractor());
        return authors.get(id);
    }

    @Override
    public Author getByNameFamily(String name, String lastName) {
        Map<String, Object> params = Map.of("name", name, "lastName", lastName);
        Map<Long, Author> authors = namedJdbc.query(
                "SELECT a.id, a.name, a.lastName, a.dateOfBirth, a.gender, b.id book_id, b.bookTitle, b.preview " +
                        "FROM Author a LEFT JOIN Book b on a.id = b.author_id " +
                        "WHERE a.name = :name AND a.lastName = :lastName",
                params,
                new AuthorResultSetExtractor());
        return new ArrayList<>(authors.values()).get(0);
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
        Map<Long, Author> authors = namedJdbc.query(
                "SELECT a.id, a.name, a.lastName, a.dateOfBirth, a.gender, b.id book_id, b.bookTitle, b.preview " +
                        "FROM Author a LEFT JOIN Book b on a.id = b.author_id",
                new AuthorResultSetExtractor());
        return new ArrayList<>(authors.values());
    }

    private static class AuthorResultSetExtractor implements ResultSetExtractor<Map<Long, Author>> {
        @Override
        public Map<Long, Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Author> authorMap = new HashMap<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                Author author = authorMap.get(id);
                if (author == null) {
                    author = new Author(
                            id,
                            rs.getString("name"),
                            rs.getString("lastName"),
                            rs.getDate("dateOfBirth"),
                            rs.getString("gender"));
                    authorMap.put(id, author);
                }
                if (rs.getLong("book_id") != 0) {
                    author.getBookList().add(new Book(
                            rs.getLong("book_id"),
                            rs.getString("bookTitle"),
                            rs.getString("preview")));
                }
            }
            return authorMap;
        }
    }
}
