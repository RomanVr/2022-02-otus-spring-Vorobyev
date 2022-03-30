package ru.homework.questionService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.homework.parse.ParseCsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("Класс Сервис вопросов")
@SpringBootTest
class QuestionServiceImplTest {
    @MockBean
    private ParseCsv parseCsv;

    @Autowired
    private QuestionService questionService;

    @DisplayName("Должен создаваться список вопросов")
    @Test
    void getQuestions() {
        List<List<String>> options = new ArrayList<>();
        options.add(Arrays.asList("1", "2", "7"));
        options.add(Arrays.asList("3", "4", "8"));
        options.add(Arrays.asList("5", "6", "9"));
        given(this.parseCsv.getDataFromCsv()).willReturn(options);
        int sizeQuestions = options.size();
        assertEquals(sizeQuestions, this.questionService.getQuestions().size());
    }
}
