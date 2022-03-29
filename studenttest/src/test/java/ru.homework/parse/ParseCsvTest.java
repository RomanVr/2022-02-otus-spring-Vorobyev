package ru.homework.parse;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Класс чтения csv")
class ParseCsvTest {

      @Configuration
//    @ComponentScan("ru.homework.parse")
//    @ConfigurationProperties(prefix = "parse")
    public static class ConfigParse {
        @Value("${parsetest.delimiter}")
        private String delimiter;
        private String filename;
        private String fileNameExt;
        @Bean
        ConfigProperties configProperties() {
            return new ConfigProperties();
        }

        @Bean
//        @ConfigurationProperties(prefix = "parsetest")
        ParseCsvImpl parseCsv(){
           /* ConfigProperties configProperties;
            ParseCsvImpl parseCsv = new ParseCsvImpl();
            parseCsv.setDelimiter(c);
            String filename;
            String fileNameExt;
            return parseCsv;*/
            return new ParseCsvImpl();
        }
    }

    @Autowired
    private ParseCsv parseCsv;
    @Autowired
    private ConfigParse configParse;
    @Autowired
    private ConfigProperties configProperties;

/*    @BeforeEach
    void setUp() {
        parseCsv = new ParseCsvImpl();
        parseCsv.setDelimiter(delimiter);
        parseCsv.setFileNameQuestion(filename);
        parseCsv.setFileNameExt(fileNameExt);
    }*/

    @DisplayName("Должен создаваться разделитель")
    @Test
    void getDelimiter() {
        assertEquals(configParse.delimiter, parseCsv.getDelimiter());
    }

   /* @DisplayName("Должно создаваться имя файла")
    @Test
    void getFileNameQuestion() {
        assertEquals(configParse.filename, parseCsv.getFileNameQuestion());
    }*/

    @DisplayName("Должен создаваться список данных из файла")
    @Test
    void getDataFromCsv() {
        List<List<String>> data = parseCsv.getDataFromCsv();
        int size = 2;
        assertEquals(size, data.size());
    }
}
