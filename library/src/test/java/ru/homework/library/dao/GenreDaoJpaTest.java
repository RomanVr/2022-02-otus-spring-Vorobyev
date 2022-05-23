package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.homework.library.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreDaoJpa.class)
@DisplayName("Dao Жанров")
class GenreDaoJpaTest {

    private static final int EXPECTED_COUNT_GENRES = 1;
    private static final String EXPECTED_TITLE_GENRE = "Java book";
    private static final int EXPECTED_ID_GENRE = 1;
    private static final String GENRE_TITLE = "genreTest";
    private static final String GENRE_TITLE_FOR_DELETE = "genreTestDelete";
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Должно получать Жанр по id")
    void getById() {
        Optional<Genre> actualGenre = genreDao.getById(EXPECTED_ID_GENRE);
        assertThat(actualGenre).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("genreTitle", EXPECTED_TITLE_GENRE);
    }

    @Test
    @DisplayName("Должно получать Жанр по названию")
    void getByTitle() {
        var actualGenre = genreDao.getByTitle(EXPECTED_TITLE_GENRE);
        assertThat(actualGenre).isNotNull()
                .hasFieldOrPropertyWithValue("genreTitle", EXPECTED_TITLE_GENRE);
    }

    @Test
    @DisplayName("Должно добавлять Жанр в БД")
    void shouldAddGenreToDB() {
        var expectedGenre = new Genre(0, GENRE_TITLE, null);
        var insertId = genreDao.save(expectedGenre).getId();
        Optional<Genre> actualGenre = genreDao.getById(insertId);
        assertThat(actualGenre).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("genreTitle", GENRE_TITLE);
    }

    @Test
    @DisplayName("Должно обновлять Жанр")
    void shouldUpdateGenre() {
        var expectedGenre = new Genre(1, GENRE_TITLE, null);
        genreDao.save(expectedGenre);
        Optional<Genre> actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("genreTitle", GENRE_TITLE);
    }

    @Test
    @DisplayName("Должно удалять жанр по id")
    void shouldDeleteGenre() {
        var expectedGenre = new Genre(0, GENRE_TITLE_FOR_DELETE, null);
        var insertId = genreDao.save(expectedGenre).getId();
        Optional<Genre> actualGenre = genreDao.getById(insertId);
        assertThat(actualGenre).isNotEmpty();

        genreDao.delete(actualGenre.get());
        em.flush();

        assertThat(genreDao.getById(insertId)).isEmpty();
    }

    @Test
    @DisplayName("Должно возвращать все Жанры")
    void shouldGetAllGenres() {
        List<Genre> actualGenreList = genreDao.getAll();
        assertThat(actualGenreList).hasSize(EXPECTED_COUNT_GENRES);
    }
}