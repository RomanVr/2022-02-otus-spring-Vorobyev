package ru.homework.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.homework.library.domain.Author;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookDaoJpa.class)
@DisplayName("Dao Книги")
class BookDaoJdbcTest {
    private static final int EXPECTED_ID_BOOK = 1;
    private static final String EXPECTED_TITLE_BOOK = "Java beginners";
    private static final String EXPECTED_NEWTITLE_BOOK = "textTest";

    private static final String EXPECTED_PREVIEW_BOOK = "text";
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final long EXPECTED_GENRE_ID = 1;
    public static final int EXPECTED_COUNT_BOOKS = 3;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Должно получать Книгу по id")
    void shouldGetBookById() {
        Optional<Book> actualBook = bookDao.getById(EXPECTED_ID_BOOK);
        assertThat(actualBook).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("bookTitle", EXPECTED_TITLE_BOOK);
    }

    @Test
    @DisplayName("Должно получать Книгу по названию")
    void shouldGetBookByTitle() {
        var actualBook = bookDao.getByTitle(EXPECTED_TITLE_BOOK);
        assertThat(actualBook).isNotNull().hasFieldOrPropertyWithValue("bookTitle", EXPECTED_TITLE_BOOK);
    }

    @Test
    @DisplayName("Должно добавлять Книгу в БД")
    void shouldAddBookToDB() {
        var expectedBook = new Book(0, EXPECTED_NEWTITLE_BOOK, "textTest", null, null, null);
        var author = em.find(Author.class, EXPECTED_AUTHOR_ID);
        var genre = em.find(Genre.class, EXPECTED_GENRE_ID);
        expectedBook.setAuthor(author);
        expectedBook.setGenre(genre);
        var insertId = bookDao.save(expectedBook).getId();
        Optional<Book> actualBook = bookDao.getById(insertId);
        assertThat(actualBook).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("bookTitle", EXPECTED_NEWTITLE_BOOK);
    }

    @Test
    @DisplayName("Должно обновлять Книгу")
    void shouldUpdateBook() {
        var expectedBook = new Book(1, EXPECTED_NEWTITLE_BOOK, "textTest", null, null, null);
        bookDao.save(expectedBook);
        Optional<Book> actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("bookTitle", EXPECTED_NEWTITLE_BOOK);
    }

    @Test
    @DisplayName("Должно удалять Книгу")
    void shouldDeleteBookById() {
        var expectedBook = new Book(0, EXPECTED_NEWTITLE_BOOK, "textTest", null, null, null);
        var author = em.find(Author.class, EXPECTED_AUTHOR_ID);
        var genre = em.find(Genre.class, EXPECTED_GENRE_ID);
        expectedBook.setAuthor(author);
        expectedBook.setGenre(genre);
        var insertId = bookDao.save(expectedBook).getId();
        var actualBook = bookDao.getById(insertId);
        assertThat(actualBook).isNotEmpty();

        bookDao.delete(actualBook.get());
        em.flush();

        assertThat(bookDao.getById(insertId)).isEmpty();
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

        assertThat(actualBookList).isNotNull().hasSize(EXPECTED_COUNT_BOOKS);
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