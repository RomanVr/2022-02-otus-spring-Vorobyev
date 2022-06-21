package ru.homework.librarymongo.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.homework.librarymongo.domain.Book;
import ru.homework.librarymongo.domain.BookCommentary;
import ru.homework.librarymongo.repositories.BookDao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.homework.librarymongo.repositories", "ru.homework.librarymongo.service"})
@DisplayName("Тестирование Service BookCommentary")
class BookCommentServiceImplTest {
    @Autowired
    private BookCommentService commentService;

    @Autowired
    private BookDao bookDao;


    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("При изменении BookCommentary должно менять в книге Commentary")
    @Test
    void shouldUpdateBookCommentary() {
        String textExpected = "new comment";
        val book = bookDao.findAll().get(0);
        val bcOld = book.getBookCommentaries().get(0);
        val bcExpected = new BookCommentary(bcOld.getId(), textExpected);

        commentService.update(bcExpected, book.getId());

        var actualBook = bookDao.findById(book.getId()).orElseThrow();
        assertThat(bookDao.findById(book.getId())).isNotEmpty()
                .get().extracting(Book::getBookCommentaries).asList()
                .element(0)
                .hasFieldOrPropertyWithValue("commentary", textExpected);
    }
}