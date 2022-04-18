package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Map.of("id", id);
        Map<Long, Genre> genres = namedJdbc.query(
                "SELECT g.id, g.genreTitle, b.id book_id, b.bookTitle, b.preview " +
                        "FROM Genre g LEFT JOIN Book b on g.id = b.genre_id WHERE g.id = :id",
                params,
                new GenreResultSetExtractor());
        return genres.get(id);
    }

    @Override
    public Genre getByTitle(String genreTitle) {
        Map<String, Object> params = Map.of("genreTitle", genreTitle);
        Map<Long, Genre> genres = namedJdbc.query(
                "SELECT g.id, g.genreTitle, b.id book_id, b.bookTitle, b.preview " +
                        "FROM Genre g LEFT JOIN Book b on g.id = b.genre_id " +
                        "WHERE g.genreTitle = :genreTitle",
                params,
                new GenreResultSetExtractor());
        return new ArrayList<>(genres.values()).get(0);
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("genreTitle", genre.getGenreTitle());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(
                "INSERT INTO Genre (genreTitle) VALUES (:genreTitle)",
                parameters,
                keyHolder,
                new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public long update(Genre genre) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", genre.getId())
                .addValue("genreTitle", genre.getGenreTitle());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update(
                "UPDATE Genre SET genreTitle =:genreTitle WHERE id =:id", parameters, keyHolder, new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Map.of("id", id);
        namedJdbc.update("DELETE FROM Genre WHERE id = :id", params);
    }

    @Override
    public List<Genre> getAll() {
        Map<Long, Genre> genres = namedJdbc.query(
                "SELECT g.id, g.genreTitle, b.id book_id, b.bookTitle, b.preview " +
                        "FROM Genre g LEFT JOIN Book b on g.id = b.genre_id",
                new GenreResultSetExtractor());
        return new ArrayList<>(genres.values());
    }

    private class GenreResultSetExtractor implements ResultSetExtractor<Map<Long, Genre>> {
        @Override
        public Map<Long, Genre> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Long, Genre> genreMap = new HashMap<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                Genre genre = genreMap.get(id);
                if (genre == null) {
                    genre = new Genre(
                            id,
                            rs.getString("genreTitle")
                    );
                    genreMap.put(id, genre);
                }
                if (rs.getLong("book_id") != 0) {
                    genre.getBookList().add(new Book(
                            rs.getLong("book_id"),
                            rs.getString("bookTitle"),
                            rs.getString("preview")));
                }
            }
            return genreMap;
        }
    }
}
