package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.homework.library.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("Dao Книги")
class BookDaoJdbcTest {
    private static final int EXPECTED_ID_BOOK = 1;
    private static final String EXPECTED_TITLE_BOOK = "Java beginners";
    private static final String EXPECTED_PREVIEW_BOOK = "text";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final long EXPECTED_GENRE_ID = 1;
    public static final int EXPECTED_COUNT_BOOKS = 1;
    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("Должно получать Книгу по id")
    void shouldGetBookById() {
        var expectedBook = new Book(EXPECTED_ID_BOOK, EXPECTED_TITLE_BOOK, EXPECTED_PREVIEW_BOOK);
        var actualBook = bookDao.getById(EXPECTED_ID_BOOK);
        assertThat(expectedBook).isEqualTo(actualBook);
    }

    @Test
    @DisplayName("Должно получать Книгу по названию")
    void shouldGetBookByTitle() {
        var expectedBook = new Book(EXPECTED_TITLE_BOOK, EXPECTED_PREVIEW_BOOK);
        var actualBook = bookDao.getByTitle(EXPECTED_TITLE_BOOK);
        assertThat(expectedBook).isEqualTo(actualBook);
    }

    @Test
    @DisplayName("Должно добавлять Книгу в БД")
    void shouldAddBookToDB() {
        var expectedBook = new Book("titleTest", "textTest");
        var insertId = bookDao.insert(expectedBook, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);
        var actualBook = bookDao.getById(insertId);
        assertThat(expectedBook).isEqualTo(actualBook);
    }

    @Test
    @DisplayName("Должно обновлять Книгу")
    void shouldUpdateBook() {
        var expectedBook = new Book(1, "titleTest", "textTest");
        bookDao.update(expectedBook);
        var actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("Должно удалять Книгу по id")
    void shouldDeleteBookById() {
        var expectedBook = new Book("titleTest", "textTest");
        var insertId = bookDao.insert(expectedBook, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID);
        assertThatCode(() -> bookDao.getById(insertId)).doesNotThrowAnyException();

        bookDao.deleteById(insertId);

        assertThatCode(() -> bookDao.getById(insertId)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    @DisplayName("Должно получать все книги")
    void shouldGetAllBooks() {
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList.size()).isEqualTo(EXPECTED_COUNT_BOOKS);
    }

    @Test
    @DisplayName("Должно находить все Книги по id Автора")
    void shouldFindBooksByAuthorId() {
        List<Book> actualBookList = bookDao.findBooksByAuthorId(EXPECTED_AUTHOR_ID);
        assertThat(actualBookList.size()).isEqualTo(EXPECTED_COUNT_BOOKS);
    }
}