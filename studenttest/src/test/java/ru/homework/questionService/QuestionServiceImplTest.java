package ru.homework.questionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.parse.ParseCsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("Класс Сервис вопросов")
@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    private ParseCsv parseCsv;

    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        List<List<String>> options = new ArrayList<>();
        options.add(Arrays.asList("1", "2", "7"));
        options.add(Arrays.asList("3", "4", "8"));
        options.add(Arrays.asList("5", "6", "9"));
        given(parseCsv.getDataFromCsv()).willReturn(options);
        questionService = new QuestionServiceImpl(parseCsv);
    }

    @DisplayName("Должен создаваться список вопросов")
    @Test
    void getQuestions() {
        int sizeQuestions = 3;
        assertEquals(sizeQuestions, this.questionService.getQuestions().size());
    }
}