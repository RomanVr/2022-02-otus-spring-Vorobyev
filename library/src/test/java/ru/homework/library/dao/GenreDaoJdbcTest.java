package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.homework.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(GenreDaoJdbc.class)
@DisplayName("Dao Жанров")
class GenreDaoJdbcTest {

    private static final int EXPECTED_COUNT_GENRES = 1;
    private static final String EXPECTED_TITLE_GENRE = "Java book";
    private static final int EXPECTED_ID_GENRE = 1;
    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("Должно получать Жанр по id")
    void getById() {
        var expectedGenre = new Genre(EXPECTED_ID_GENRE, EXPECTED_TITLE_GENRE);
        var actualGenre = genreDao.getById(EXPECTED_ID_GENRE);
        assertThat(expectedGenre).isEqualTo(actualGenre);
    }

    @Test
    @DisplayName("Должно получать Жанр по названию")
    void getByTitle() {
        var expectedGenre = new Genre(EXPECTED_TITLE_GENRE);
        var actualGenre = genreDao.getByTitle(EXPECTED_TITLE_GENRE);
        assertThat(expectedGenre).isEqualTo(actualGenre);
    }

    @Test
    @DisplayName("Должно добавлять Жанр в БД")
    void shouldAddGenreToDB() {
        var expectedGenre = new Genre("genreTest");
        var insertId = genreDao.insert(expectedGenre);
        var actualGenre = genreDao.getById(insertId);
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("Должно обновлять Жанр")
    void shouldUpdateGenre() {
        var expectedGenre = new Genre(1, "genreTest");
        genreDao.update(expectedGenre);
        var actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("Должно удалять жанр по id")
    void shouldDeleteGenre() {
        var expectedGenre = new Genre("genreTest");
        var insertId = genreDao.insert(expectedGenre);
        assertThatCode(() -> genreDao.getById(insertId)).doesNotThrowAnyException();

        genreDao.deleteById(insertId);

        assertThatCode(() -> genreDao.getById(insertId)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("Должно возвращать все Жанры")
    void shouldGetAllGenres() {
        List<Genre> actualGenreList = genreDao.getAll();
        assertThat(actualGenreList.size()).isEqualTo(EXPECTED_COUNT_GENRES);
    }
}