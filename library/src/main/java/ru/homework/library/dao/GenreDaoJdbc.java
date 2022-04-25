package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return namedJdbc.queryForObject(
                "SELECT id, genreTitle FROM Genre WHERE id = :id",
                params,
                new GenreMapper());
    }

    @Override
    public Genre getByTitle(String genreTitle) {
        Map<String, Object> params = Map.of("genreTitle", genreTitle);
        return namedJdbc.queryForObject(
                "SELECT id, genreTitle FROM Genre WHERE genreTitle = :genreTitle",
                params,
                new GenreMapper());
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
        return namedJdbc.query(
                "SELECT id, genreTitle FROM Genre", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String genreTitle = rs.getString("genreTitle");
            return new Genre(id, genreTitle);
        }
    }
}
