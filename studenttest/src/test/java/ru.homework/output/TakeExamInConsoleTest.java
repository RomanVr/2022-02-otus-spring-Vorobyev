package ru.homework.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.homework.domain.Person;
import ru.homework.domain.Question;
import ru.homework.ioService.IOService;
import ru.homework.questionService.QuestionServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestPropertySource("classpath:application–test.properties")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Класс проведения экзамена в консоли")
class TakeExamInConsoleTest {
    private Person person;
    @Mock
    private QuestionServiceImpl questionService;
    @Mock
    private IOService ioService;

    @Value("${exam.name}")
    private String nameExam;
    @Value("${exam.delimiterAnswers}")
    private String delimiterAnswers;
    @Value("${outPut.separatorLine}")
    private String separatorLine;
    @Value("${exam.symbolNumber}")
    private String numberQuestion;

    private TakeExam takeExam;

    @BeforeEach
    void setUp() {
        List<Question> questions = initQuestions();
        given(questionService.getQuestions()).willReturn(questions);
        takeExam = new TakeExamInConsole(
                questionService,
                ioService);
        person = new Person(new HashMap<>(), "name");
    }

    @Test
    void runExam() {
    }

    @DisplayName("Должно устанавливаться имя пользователя")
    @Test
    void askName() {
        String name = "UserName";
        given(ioService.readWithPrompt(anyString())).willReturn(name);
        assertEquals(name, this.takeExam.askName());
    }

    @DisplayName("Должны выводиться вопросы и записываться ответы")
    @Test
    void outputQuestions() {
        int countQuestion = 3;
        String answer = "1";
        given(ioService.readWithPrompt(anyString())).willReturn(answer);
        this.takeExam.outputQuestions(person);
        verify(ioService, times(countQuestion)).readWithPrompt(anyString());
        assertEquals(countQuestion, person.getAnswers().size());
    }

    @DisplayName("Должны выводиться ответы пользователя")
    @Test
    void outPutAnswersClient() {
        person.getAnswers().put(1, "1");
        person.getAnswers().put(2, "1");
        this.takeExam.outPutAnswersClient(person);
        verify(ioService, times(person.getAnswers().size())).outputString(anyString());
    }

    @DisplayName("Должен рассчитываться результат тестирования")
    @Test
    void outPutResult() {
        Map<Integer, String> answers = new HashMap<>();
        answers.put(0, "2");
        answers.put(1, "4");
        answers.put(2, "6");
        String result = "3";
        person = new Person(answers, "T");
        this.takeExam.outPutResult(person);
        String outStr = "Result for " + person.getName() + " - " + result;
        verify(ioService, times(1)).outputString(outStr);
    }

    @DisplayName("Должно заканчиваться тестирование")
    @Test
    void isContinueToFalse() {
        String inputExit = "exit";
        given(ioService.readWithPrompt(anyString())).willReturn(inputExit);
        assertFalse(takeExam.isContinue());
    }

    @DisplayName("Тестирование должно продолжаться")
    @Test
    void isContinueToTrue() {
        given(ioService.readWithPrompt(anyString())).willReturn(anyString());
        assertTrue(takeExam.isContinue());
    }

    List<Question> initQuestions() {
        List<Question> questions = new ArrayList<>();
        int id_0 = 0;
        int id_1 = 1;
        int id_2 = 2;

        String rightAnswerId_0 = "2";
        String rightAnswerId_1 = "4";
        String rightAnswerId_2 = "6";

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();
        question1.setId(id_0);
        question1.setQuestionName("1");
        question1.setRightAnswers(rightAnswerId_0);
        question1.setAnswerOptions(List.of("7"));
        question2.setId(id_1);
        question2.setQuestionName("3");
        question2.setRightAnswers(rightAnswerId_1);
        question2.setAnswerOptions(List.of("8"));
        question3.setId(id_2);
        question3.setQuestionName("5");
        question3.setRightAnswers(rightAnswerId_2);
        question3.setAnswerOptions(List.of("9"));
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        return questions;
    }
}