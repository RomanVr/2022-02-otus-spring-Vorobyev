package ru.homework.librarymongo.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.MappingException;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.domain.Book;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("ru.homework.librarymongo.repositories")
@DisplayName("Тестирование Author Repo")
class AuthorDaoTest {
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;

    @DisplayName("Должен кидать mappingException во время сохранения Author с отсутствующей книгой")
    @Test
    void shouldThrowMappingExceptionAuthorWithNewBook() {
        val newBook = new Book("newBook", "text book", null, null);
        val author = new Author("nameTest", "lastNameTest", Date.valueOf("1990-05-05"), "man");
        author.getBookList().add(newBook);
        assertThatThrownBy(() -> authorDao.save(author)).isInstanceOf(MappingException.class);
    }


}
