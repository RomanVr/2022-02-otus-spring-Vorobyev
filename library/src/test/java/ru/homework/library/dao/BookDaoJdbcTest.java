package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.homework.library.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookDaoJpa.class)
@DisplayName("Dao Книги")
class BookDaoJdbcTest {
    private static final int EXPECTED_ID_BOOK = 1;
    private static final String EXPECTED_TITLE_BOOK = "Java beginners";
    private static final String EXPECTED_PREVIEW_BOOK = "text";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final long EXPECTED_GENRE_ID = 1;
    public static final int EXPECTED_COUNT_BOOKS = 3;
    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("Должно получать Книгу по id")
    void shouldGetBookById() {
        var expectedBook = new Book(EXPECTED_ID_BOOK, EXPECTED_TITLE_BOOK, EXPECTED_PREVIEW_BOOK, null, null, null);
        var actualBook = bookDao.getById(EXPECTED_ID_BOOK).get();
        assertThat(expectedBook).isEqualTo(actualBook);
    }

    @Test
    @DisplayName("Должно получать Книгу по названию")
    void shouldGetBookByTitle() {
        var expectedBook = new Book(0, EXPECTED_TITLE_BOOK, EXPECTED_PREVIEW_BOOK, null, null, null);
        var actualBook = bookDao.getByTitle(EXPECTED_TITLE_BOOK);
        assertThat(expectedBook).isEqualTo(actualBook);
    }

    @Test
    @DisplayName("Должно добавлять Книгу в БД")
    void shouldAddBookToDB() {
        var expectedBook = new Book(0, "titleTest", "textTest", null, null, null);
        var insertId = bookDao.save(expectedBook).getId();
        var actualBook = bookDao.getById(insertId).get();
        assertThat(expectedBook).isEqualTo(actualBook);
    }

    @Test
    @DisplayName("Должно обновлять Книгу")
    void shouldUpdateBook() {
        var expectedBook = new Book(1, "titleTest", "textTest", null, null, null);
        bookDao.save(expectedBook);
        var actualBook = bookDao.getById(expectedBook.getId()).get();
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("Должно удалять Книгу по id")
    void shouldDeleteBookById() {
        var expectedBook = new Book(0, "titleTest", "textTest", null, null, null);
        var insertId = bookDao.save(expectedBook).getId();
        assertThatCode(() -> bookDao.getById(insertId)).doesNotThrowAnyException();

        bookDao.deleteById(insertId);

        assertThatCode(() -> bookDao.getById(insertId)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("Должно получать все книги")
    void shouldGetAllBooks() {
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList).isNotNull().hasSize(EXPECTED_COUNT_BOOKS)
                .allMatch(book -> !book.getBookTitle().equals(""))
                .allMatch(book -> book.getBookCommentaries() != null && book.getBookCommentaries().size() > 0);
    }

    @Test
    @DisplayName("Должно находить все Книги по id Автора")
    void shouldFindBooksByAuthorId() {
        List<Book> actualBookList = bookDao.findBooksByAuthorId(EXPECTED_AUTHOR_ID);
        assertThat(actualBookList.size()).isEqualTo(EXPECTED_COUNT_BOOKS);
    }

    @Test
    @DisplayName("Должно находить все Книги по id Жанра")
    void shouldFindBooksByGenreId() {
        List<Book> actualBookList = bookDao.findBooksByGenreId(EXPECTED_GENRE_ID);
        assertThat(actualBookList).isNotNull().hasSize(EXPECTED_COUNT_BOOKS)
                .allMatch(book -> !book.getBookTitle().equals(""))
                .allMatch(book -> book.getBookCommentaries() != null && book.getBookCommentaries().size() > 0);
    }
}