package ru.homework.examService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.homework.domain.Exam;
import ru.homework.domain.Person;
import ru.homework.domain.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@DisplayName("Класс сервис экзамена")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExamServiceImplTest {
    @Mock
    private Exam exam;
    @Mock
    private AnnotationConfigApplicationContext applicationContext;

    private ExamService examService;
    private String delimiterAnswers = ",";
    final String nameExam = "testExam";

    private String nameUser = "user";

    private int id_0 = 0;
    private int id_1 = 1;
    private int id_2 = 2;

    private String rightAnswerId_0 = "2";
    private String rightAnswerId_1 = "4";
    private String rightAnswerId_2 = "6";

    @BeforeEach
    void setUp() {
        List<Question> questions = new ArrayList<>();
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

        String rightAnswers = "1,2,3";

        given(applicationContext.getBean(Person.class)).willReturn(new Person(new HashMap<>()));
        given(exam.getNameExam()).willReturn(nameExam);
        given(exam.getQuestions()).willReturn(questions);
        given(exam.getAnswersByIdQuestion(eq(id_2))).willReturn(question3.getAnswerOptions());
        given(exam.getRightAnswerById(eq(id_0))).willReturn(rightAnswers);
        examService = new ExamServiceImpl(exam, applicationContext, delimiterAnswers);
    }

    @DisplayName("Должно выводиться имя экзамена")
    @Test
    void getNameExam() {
        assertEquals(nameExam, examService.getNameExam());
    }

    @DisplayName("Должен создаваться пользователь")
    @Test
    void startExam() {
        this.examService.startExam();
        this.examService.setPersonName(this.nameUser);
        assertEquals(this.nameUser, examService.getNamePerson());
    }

    @DisplayName("Должны добавляться ответы пользователя")
    @Test
    void addAnswer() {
        this.examService.startExam();
        int id = 1;
        String answer = "good";
        int sizeAnswers = 1;
        this.examService.addAnswer(id, answer);
        assertEquals(sizeAnswers, this.examService.getAnswersPerson().size());
    }

    @DisplayName("Должен сохраняться пользователь")
    @Test
    void savePerson() {
        assertDoesNotThrow(this.examService::savePerson);
    }

    @DisplayName("Должен выводится список вопросов")
    @Test
    void getQuestions() {
        int size = 3;
        assertEquals(size, examService.getQuestions().size());
    }

    @Test
    void getAnswersByIdQuestion() {
        assertEquals(List.of("9"), examService.getAnswersByIdQuestion(id_2));
    }

    @DisplayName("Должны совпадать ответы")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3", "2,1,3", "3,1,2"})
    void arrayEqualToTrue(String answer) {
        int id = 0;
        String resultRight = "1";
        this.examService.startExam();
        this.examService.addAnswer(id, answer);
        assertEquals(resultRight, examService.getResult());
    }

    @DisplayName("Должен быть результат 0, ответы не правильные")
    @ParameterizedTest
    @ValueSource(strings = {"1,2", "1,1,3", "3"})
    void arrayEqualToFalse(String answer) {
        int id = 0;
        String resultRight = "0";
        this.examService.startExam();
        this.examService.addAnswer(id, answer);
        assertEquals(resultRight, examService.getResult());
    }
}