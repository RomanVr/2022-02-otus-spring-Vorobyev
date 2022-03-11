package ru.homework.questionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.parse.ParseCsv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private ParseCsv parseCsv;

    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        List<List<String>> options = new ArrayList<>();
        String[] option1 = {"1", "2", "7"};
        String[] option2 = {"3", "4", "8"};
        String[] option3 = {"5", "6", "9"};
        options.add(Arrays.asList(option1));
        options.add(Arrays.asList(option2));
        options.add(Arrays.asList(option3));
        given(parseCsv.getDataFromCsv()).willReturn(options);
        questionService = new QuestionServiceImpl(parseCsv);
    }

    @Test
    void getQuestions() {

    }
}