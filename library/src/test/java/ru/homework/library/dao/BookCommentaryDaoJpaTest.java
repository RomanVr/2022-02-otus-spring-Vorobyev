package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.BookCommentary;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Dao Комментариев")
class BookCommentaryDaoJpaTest {
    public static final int EXPECTED_COUNT_COMMENTS = 3;
    private static final long EXPECTED_COMMENTARY_ID = 1L;
    private static final String EXPECTED_COMMENTARY_TEXT = "Very good book";
    private static final long EXPECTED_BOOK_ID = 1;
    @Autowired
    private BookCommentaryDao commentaryDao;
    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Должно получать комментарий по его id")
    void shouldGetCommentaryById() {
        Optional<BookCommentary> actualCommentary = commentaryDao.findById(EXPECTED_COMMENTARY_ID);
        assertThat(actualCommentary).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("commentary", EXPECTED_COMMENTARY_TEXT);
    }

    @Test
    @DisplayName("Должно добавлять комментарий в БД")
    void shouldAddCommentaryToDb() {
        var expectedComm = new BookCommentary(0, EXPECTED_COMMENTARY_TEXT, null);
        var book = em.find(Book.class, EXPECTED_BOOK_ID);
        expectedComm.setBook(book);
        var insertId = commentaryDao.save(expectedComm).getId();
        Optional<BookCommentary> actualComm = commentaryDao.findById(insertId);
        assertThat(actualComm).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("commentary", EXPECTED_COMMENTARY_TEXT);
    }

    @Test
    @DisplayName("Должно удалять комментарий по его id")
    void shouldDeleteCommentaryById() {
        var expectedComm = new BookCommentary(0, EXPECTED_COMMENTARY_TEXT, null);
        var book = em.find(Book.class, EXPECTED_BOOK_ID);
        expectedComm.setBook(book);
        var insertId = commentaryDao.save(expectedComm).getId();
        Optional<BookCommentary> actualComm = commentaryDao.findById(insertId);
        assertThat(actualComm).isNotEmpty();

        commentaryDao.delete(actualComm.get());
        em.flush();

        assertThat(commentaryDao.findById(insertId)).isEmpty();
    }
}
