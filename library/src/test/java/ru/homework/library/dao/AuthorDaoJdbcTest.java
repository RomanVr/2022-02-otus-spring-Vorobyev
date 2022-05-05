package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.homework.library.domain.Author;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@JdbcTest
@Import(AuthorDaoJpa.class)
@DisplayName("Dao Автора")
class AuthorDaoJdbcTest {

    private static final int EXPECTED_COUNT_AUTHORS = 3;
    private static final int EXPECTED_ID_AUTHOR = 1;
    private static final String EXPECTED_NAME_AUTHOR = "ivan";
    private static final String EXPECTED_LAST_NAME_AUTHOR = "ivanov";
    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("Должно получать Автора по id")
    void shouldGetAuthorById() {
        var expectedAuthor = new Author(0, "ivan", "ivanov", Date.valueOf("2020-01-01"), "man");
        var actualAuthor = authorDao.getById(EXPECTED_ID_AUTHOR).get();
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("Должно получать Автора по имени и фамилии")
    void shouldGetAuthorByNameFamily() {
        var expectedAuthor = new Author(0, "ivan", "ivanov", Date.valueOf("2020-01-01"), "man");
        var actualAuthor = authorDao.getByNameFamily(EXPECTED_NAME_AUTHOR, EXPECTED_LAST_NAME_AUTHOR);
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("Должно добавлять Автора в БД")
    void shouldAddAuthorToDB() {
        var expectedAuthor = new Author(0, "name", "testFamily", Date.valueOf("1978-01-01"), "man");
        var idInsert = authorDao.save(expectedAuthor).getId();
        var actualAuthor = authorDao.getById(idInsert).get();
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("Должно обновлять Автора")
    void shouldUpdateAuthor() {
        var expectedAuthor = new Author(1, "sergey", "ivanov", Date.valueOf("2020-01-01"), "man");
        authorDao.save(expectedAuthor);
        var actualAuthor = authorDao.getById(expectedAuthor.getId()).get();
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("Должно удалять Автора по Id")
    void shouldDeleteAuthorById() {
        var expectedAuthor = new Author(0, "name", "testLastName", Date.valueOf("1978-01-01"), "man");
        var insertId = authorDao.save(expectedAuthor).getId();
        assertThatCode(() -> authorDao.getById(insertId)).doesNotThrowAnyException();

        authorDao.deleteById(insertId);

        assertThatCode(() -> authorDao.getById(insertId)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("Должен получать всех авторов")
    void shouldGetAllAuthors() {
        List<Author> actualAuthorList = authorDao.getAll();
        assertThat(actualAuthorList.size()).isEqualTo(EXPECTED_COUNT_AUTHORS);
    }
}