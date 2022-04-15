package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations namedJdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return namedJdbc.queryForObject(
                "SELECT id, bookTitle, preview, author_id, genre_id " +
                        "FROM Book WHERE id = :id", params, new BookMapper(authorDao, genreDao));
    }

    @Override
    public Book getByTitle(String title) {
        Map<String, Object> params = Map.of("bookTitle", title);
        return namedJdbc.queryForObject(
                "SELECT id, bookTitle, preview, author_id, genre_id " +
                        "FROM Book WHERE bookTitle = :bookTitle", params, new BookMapper(authorDao, genreDao));
    }

    @Override
    public long insert(Book book) {
        

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("bookTitle", book.getBookTitle())
                .addValue("preview", book.getPreview())
                .addValue("dateOfBirth", book.getPreview()))
                .addValue("gender", author.getGender());
        KeyHolder keyHolder = new GeneratedKeyHolder();


        namedJdbc.update(
                "INSERT INTO Author (name, family, dateOfBirth, gender) " +
                        "VALUES (:name, :family, :dateOfBirth, :gender)", parameters, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public long update(Book book) {
        return 0;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<Book> getAll() {
        return namedJdbc.query(
                "SELECT id, bookTitle, preview, author_id, genre_id " +
                        "FROM Book", new BookMapper(authorDao, genreDao));
    }

    @RequiredArgsConstructor
    @Setter
    private static class BookMapper implements RowMapper<Book> {
        private final AuthorDao authorDao;
        private final GenreDao genreDao;

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String bookTitle = rs.getString("bookTitle");
            String preview = rs.getString("preview");
            long author_id = rs.getLong("author_id");
            long genre_id = rs.getLong("genre_id");
            Author author = authorDao.getById(author_id);
            Genre genre = genreDao.getById(genre_id);
            return new Book(id, bookTitle, preview, author, genre);
        }
    }
}
