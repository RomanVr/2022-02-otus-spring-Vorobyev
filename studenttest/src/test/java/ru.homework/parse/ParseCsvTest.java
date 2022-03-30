package ru.homework.parse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Класс чтения csv")
class ParseCsvTest {
    @Autowired
    private ParseCsv parseCsv;

    @DisplayName("Должен создаваться список данных из файла")
    @Test
    void getDataFromCsv() {
        List<List<String>> data = parseCsv.getDataFromCsv();
        int size = 2;
        assertEquals(size, data.size());
    }
}
