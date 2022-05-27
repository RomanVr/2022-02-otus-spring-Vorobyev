package ru.homework.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.homework.library.dao.AuthorDao;
import ru.homework.library.dao.BookDao;
import ru.homework.library.dao.GenreDao;
import ru.homework.library.domain.Author;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.Genre;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис Книги")
@SpringBootTest
class BookServiceImplTest {
    private static final long EXPECTED_ID_BOOK = 1;
    private static final String EXPECTED_TITLE_BOOK = "bookTitle";
    private static final Book EXPECTED_BOOK = new Book(EXPECTED_ID_BOOK, EXPECTED_TITLE_BOOK, "bookText", null, null, null);
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final Author EXPECTED_AUTHOR = new Author(
            EXPECTED_AUTHOR_ID,
            "testAuthor",
            "lastNameAuthor",
            Date.valueOf("2000-01-01"),
            "man",
            List.of(EXPECTED_BOOK));
    private static final long EXPECTED_GENRE_ID = 1;
    public static final int EXPECTED_COUNT_BOOKS = 1;
    @Autowired
    private BookService bookService;

    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;

    @Test
    @DisplayName("Должен получать книгу по id")
    void shouldGetBookById() {
        given(bookDao.findById(EXPECTED_ID_BOOK)).willReturn(Optional.of(EXPECTED_BOOK));
        assertThat(bookService.getById(EXPECTED_ID_BOOK)).isNotEmpty();
    }

    @Test
    @DisplayName("Должен получать Книгу по названию")
    void shouldGetBookByTitle() {
        given(bookDao.findByBookTitle(EXPECTED_TITLE_BOOK)).willReturn(EXPECTED_BOOK);
        assertThat(bookService.getByTitle(EXPECTED_TITLE_BOOK)).isEqualTo(EXPECTED_BOOK);
    }

    @Test
    @DisplayName("Должен добавлять Книгу в БД")
    void shouldAddBookToDB() {
        given(authorDao.findById(EXPECTED_AUTHOR_ID)).willReturn(Optional.of(new Author()));
        given(genreDao.findById(EXPECTED_GENRE_ID)).willReturn(Optional.of(new Genre()));
        given(bookDao.save(EXPECTED_BOOK)).willReturn(EXPECTED_BOOK);
        assertThat(bookService.insert(EXPECTED_BOOK, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID)).isEqualTo(EXPECTED_AUTHOR_ID);
    }

    @Test
    @DisplayName("Должен обновлять Книгу")
    void shouldUpdateBook() {
        given(bookDao.save(EXPECTED_BOOK)).willReturn(EXPECTED_BOOK);
        assertThat(bookService.update(EXPECTED_BOOK)).isEqualTo(EXPECTED_AUTHOR_ID);
    }

/*
    @Test
    @DisplayName("Должен удалять Книгу по id")
    void shouldDeleteBookById() {
    }
*/

    @Test
    @DisplayName("Должен получать все Книги")
    void shouldGetAllBooks() {
        given(bookDao.findAll()).willReturn(List.of(EXPECTED_BOOK));
        assertThat(bookService.getAll().size()).isEqualTo(EXPECTED_COUNT_BOOKS);
    }

    @Test
    @DisplayName("Должен искать Книги по id автора")
    void shouldFindBooksByAuthorId() {
        given(authorDao.getById(EXPECTED_AUTHOR_ID)).willReturn(EXPECTED_AUTHOR);
        assertThat(bookService.findBooksByAuthorId(EXPECTED_AUTHOR_ID).size()).isEqualTo(EXPECTED_COUNT_BOOKS);
    }
}