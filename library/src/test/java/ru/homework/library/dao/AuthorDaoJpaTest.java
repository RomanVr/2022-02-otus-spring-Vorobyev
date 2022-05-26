package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.homework.library.domain.Author;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Import(AuthorDao.class)
@DisplayName("Dao Автора")
class AuthorDaoJpaTest {

    private static final int EXPECTED_COUNT_AUTHORS = 3;
    private static final long EXPECTED_ID_AUTHOR = 1L;
    private static final String EXPECTED_NAME_AUTHOR = "ivan";
    private static final String EXPECTED_LAST_NAME_AUTHOR = "ivanov";
    private static final String EXPECTED_NEW_NAME = "sergey";
    private static final String EXPECTED_NAME = "name";
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Должно получать Автора по id")
    void shouldGetAuthorById() {
        Optional<Author> actualAuthor = authorDao.findById(EXPECTED_ID_AUTHOR);
        assertThat(actualAuthor).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("name", EXPECTED_NAME_AUTHOR);
    }

    @Test
    @DisplayName("Должно получать Автора по имени и фамилии")
    void shouldGetAuthorByNameFamily() {
        var actualAuthor = authorDao.getByNameAndLastName(EXPECTED_NAME_AUTHOR, EXPECTED_LAST_NAME_AUTHOR);
        assertThat(actualAuthor).isNotNull().hasFieldOrPropertyWithValue("name", EXPECTED_NAME_AUTHOR);
    }

    @Test
    @DisplayName("Должно добавлять Автора в БД")
    void shouldAddAuthorToDB() {
        var expectedAuthor = new Author(0, EXPECTED_NAME, "testFamily", Date.valueOf("1978-01-01"), "man", null);
        var idInsert = authorDao.save(expectedAuthor).getId();
        Optional<Author> actualAuthor = authorDao.findById(idInsert);
        assertThat(actualAuthor).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("name", EXPECTED_NAME);
    }

    @Test
    @DisplayName("Должно обновлять Автора")
    void shouldUpdateAuthor() {
        var expectedAuthor = new Author(1, EXPECTED_NEW_NAME, "ivanov", Date.valueOf("2020-01-01"), "man", null);
        authorDao.save(expectedAuthor);
        Optional<Author> actualAuthor = authorDao.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("name", EXPECTED_NEW_NAME);
    }

    @Test
    @DisplayName("Должно удалять Автора по Id")
    void shouldDeleteAuthorById() {
        var expectedAuthor = new Author(0, EXPECTED_NAME, "testLastName", Date.valueOf("1978-01-01"), "man", null);
        var insertId = authorDao.save(expectedAuthor).getId();
        var actualAuthor = authorDao.findById(insertId);
        assertThat(actualAuthor).isNotEmpty();

        authorDao.delete(actualAuthor.get());
        em.flush();

        assertThat(authorDao.findById(insertId)).isEmpty();
    }

    @Test
    @DisplayName("Должен получать всех авторов")
    void shouldGetAllAuthors() {
        List<Author> actualAuthorList = authorDao.findAll();
        assertThat(actualAuthorList).hasSize(EXPECTED_COUNT_AUTHORS);
    }
}