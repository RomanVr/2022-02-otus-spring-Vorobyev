package ru.homework.librarymongo.service;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.repositories.AuthorDao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.homework.librarymongo.repositories", "ru.homework.librarymongo.service"})
@DisplayName("Тестирование Service Book")
class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorDao authorDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("При удалении Book должно удаляться из Author")
    @Test
    void shouldRemovedBookFromAuthorWhenIsDeleted() {
        val author = authorDao.findAll().get(0);
        val expectedSizeAuthorBookList = author.getBookList().size() - 1;
        val book = author.getBookList().get(0);

        bookService.deleteById(book.getId());

        assertThat(authorDao.findById(author.getId())).isNotEmpty()
                .get().extracting(Author::getBookList).asList()
                .hasSize(expectedSizeAuthorBookList);
    }
}