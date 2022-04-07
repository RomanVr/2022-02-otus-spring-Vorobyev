package ru.homework.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.homework.domain.Person;
import ru.homework.domain.Question;
import ru.homework.ioService.IOService;
import ru.homework.questionService.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("Класс проведения экзамена в консоли")
class TakeExamInConsoleTest {

    @MockBean
    private QuestionServiceImpl questionService;
    @MockBean
    private IOService ioService;
    @Autowired
    @Qualifier("TakeExamInConsole")
    private TakeExam takeExam;

    private Person person;

    @BeforeEach
    void setUp() {
        List<Question> questions = initQuestions();
        given(questionService.getQuestions()).willReturn(questions);

        person = new Person(new HashMap<>(), "name");
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
        String outStr = "Результат для пользователя " + person.getName() + " - " + result;
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
        given(ioService.readWithPrompt(anyString())).willReturn("");
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

        Question question1 = new Question(id_0, "1", List.of("7"), rightAnswerId_0);
        Question question2 = new Question(id_1, "3", List.of("8"), rightAnswerId_1);
        Question question3 = new Question(id_2, "5", List.of("9"), rightAnswerId_2);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        return questions;
    }
}
