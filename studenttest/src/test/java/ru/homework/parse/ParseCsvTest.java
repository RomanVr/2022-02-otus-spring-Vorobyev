package ru.homework.parse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("classpath:application–test.properties")
@ExtendWith(SpringExtension.class)
@DisplayName("Класс чтения csv")
class ParseCsvTest {
    @Value("${delimiter}")
    private String delimiter;

    @Value("${pathfile}")
    private String filename;

    private ParseCsvImpl parseCsv;

    @BeforeEach
    void setUp() {
        parseCsv = new ParseCsvImpl(delimiter, filename);
    }

    @DisplayName("Должен создаваться разделитель")
    @Test
    void getDelimiter() {
        assertEquals(delimiter, parseCsv.getDelimiter());
    }

    @DisplayName("Должно создаваться имя файла")
    @Test
    void getFileNameQuestion() {
        assertEquals(filename, parseCsv.getFileNameQuestion());
    }

    @Test
    void getDataFromCsv() {
        List<List<String>> data = parseCsv.getDataFromCsv();
        int size = 2;
        assertEquals(size, data.size());
    }
}
