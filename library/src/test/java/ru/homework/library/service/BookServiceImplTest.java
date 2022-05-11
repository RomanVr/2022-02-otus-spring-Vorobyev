package ru.homework.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.homework.library.dao.BookDao;
import ru.homework.library.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис Книги")
@SpringBootTest
class BookServiceImplTest {
    private static final long EXPECTED_ID_BOOK = 1;
    private static final String EXPECTED_TITLE_BOOK = "bookTitle";
    private static final Book expectedBook = new Book(EXPECTED_ID_BOOK, EXPECTED_TITLE_BOOK, "bookText", null, null, null);
    private static final long EXPECTED_AUTHOR_ID = 1;
    private static final long EXPECTED_GENRE_ID = 1;
    public static final int EXPECTED_COUNT_BOOKS = 1;
    @Autowired
    private BookService bookService;

    @MockBean
    private BookDao bookDao;

    @Test
    @DisplayName("Должен получать книгу по id")
    void shouldGetBookById() {
        given(bookDao.getById(EXPECTED_ID_BOOK)).willReturn(Optional.of(expectedBook));
        assertThat(bookService.getById(EXPECTED_ID_BOOK)).isNotEmpty();
    }

    @Test
    @DisplayName("Должен получать Книгу по названию")
    void shouldGetBookByTitle() {
        given(bookDao.getByTitle(EXPECTED_TITLE_BOOK)).willReturn(expectedBook);
        assertThat(bookService.getByTitle(EXPECTED_TITLE_BOOK)).isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("Должен добавлять Книгу в БД")
    void shouldAddBookToDB() {
        given(bookDao.save(expectedBook)).willReturn(expectedBook);
        assertThat(bookService.insert(expectedBook, EXPECTED_AUTHOR_ID, EXPECTED_GENRE_ID)).isEqualTo(EXPECTED_AUTHOR_ID);
    }

    @Test
    @DisplayName("Должен обновлять Книгу")
    void shouldUpdateBook() {
        given(bookDao.save(expectedBook)).willReturn(expectedBook);
        assertThat(bookService.update(expectedBook)).isEqualTo(EXPECTED_AUTHOR_ID);
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
        given(bookDao.getAll()).willReturn(List.of(expectedBook));
        assertThat(bookService.getAll().size()).isEqualTo(EXPECTED_COUNT_BOOKS);
    }

    @Test
    @DisplayName("Должен искать Книги по id автора")
    void shouldFindBooksByAuthorId() {
        given(bookDao.findBooksByAuthorId(EXPECTED_AUTHOR_ID)).willReturn(List.of(expectedBook));
        assertThat(bookService.findBooksByAuthorId(EXPECTED_AUTHOR_ID).size()).isEqualTo(EXPECTED_COUNT_BOOKS);
    }
}