package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.Author;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return namedJdbc.queryForObject(
                "SELECT b.id, b.bookTitle, b.preview, " +
                        "a.id author_id, a.name, a.lastName, a.dateOfBirth, a.gender, " +
                        "g.id genre_id, g.genreTitle " +
                        "FROM Book b JOIN Author a ON b.author_id = a.id " +
                        "JOIN Genre g ON b.genre_id = g.id " +
                        "WHERE b.id = :id", params, new BookMapper());
    }

    @Override
    public Book getByTitle(String title) {
        Map<String, Object> params = Map.of("bookTitle", title);
        return namedJdbc.queryForObject(
                "SELECT b.id, b.bookTitle, b.preview, " +
                        "a.id author_id, a.name, a.lastName, a.dateOfBirth, a.gender, " +
                        "g.id genre_id, g.genreTitle " +
                        "FROM Book b JOIN Author a ON b.author_id = a.id " +
                        "JOIN Genre g ON b.genre_id = g.id " +
                        "WHERE b.bookTitle = :bookTitle", params, new BookMapper());
    }

    @Override
    public long insert(Book book, long author_id, long genre_id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("bookTitle", book.getBookTitle())
                .addValue("preview", book.getPreview())
                .addValue("author_id", author_id)
                .addValue("genre_id", genre_id);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(
                "INSERT INTO Book (bookTitle, preview, author_id, genre_id) " +
                        "VALUES (:bookTitle, :preview, :author_id, :genre_id)",
                parameters,
                keyHolder,
                new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public long update(Book book) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("bookTitle", book.getBookTitle())
                .addValue("preview", book.getPreview());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(
                "UPDATE Book SET bookTitle = :bookTitle, preview = :preview " +
                        "WHERE id =:id", parameters, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Map.of("id", id);
        namedJdbc.update("DELETE FROM Book WHERE id = :id", params);
    }

    @Override
    public List<Book> getAll() {
        return namedJdbc.query(
                "SELECT id, bookTitle, preview " +
                        "FROM Book", new BookMapperAll());
    }

    @Override
    public List<Book> findBooksByAuthorId(long author_id) {
        Map<String, Object> params = Map.of("author_id", author_id);
        return namedJdbc.query(
                "SELECT id, bookTitle, preview " +
                        "FROM Book WHERE author_id = :author_id", params, new BookMapperAll());
    }

    @Override
    public List<Book> findBooksByGenreId(long genre_id) {
        //TODO not implemented!!!
        Map<String, Object> params = Map.of("genre_id", genre_id);
        return namedJdbc.query(
                "SELECT id, bookTitle, preview " +
                        "FROM Book WHERE genre_id = :genre_id", params, new BookMapperAll());
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String bookTitle = rs.getString("bookTitle");
            String preview = rs.getString("preview");
            Author author = new Author(
                    rs.getLong("author_id"),
                    rs.getString("name"),
                    rs.getString("lastName"),
                    rs.getDate("dateOfBirth"),
                    rs.getString("gender"));
            Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("genreTitle"));
            return new Book(id, bookTitle, preview, author, genre);
        }
    }

    private static class BookMapperAll implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String bookTitle = rs.getString("bookTitle");
            String preview = rs.getString("preview");
            return new Book(id, bookTitle, preview);
        }
    }
}
