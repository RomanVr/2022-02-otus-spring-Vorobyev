package ru.homework.parse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Класс чтения csv")
class ParseCsvTest {

    private static final String DELIMITER = ",";
    private static final String FILENAME = "file.csv";
    private ParseCsvImpl parseCsv;
    @BeforeEach
    void init() {
        parseCsv = new ParseCsvImpl(DELIMITER, FILENAME);
    }

    @DisplayName("Должен создаваться разделитель")
    @Test
    void getDelimiter() {
        assertEquals(DELIMITER, parseCsv.getDelimiter());
    }
    @DisplayName("Должно создаваться имя файла")
    @Test
    void getFileNameQuestion() {
        assertEquals(FILENAME, parseCsv.getFileNameQuestion());
    }

    @Disabled
    @Test
    void getDataFromCsv() {
    }
}
