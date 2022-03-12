package ru.homework.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.homework.domain.Exam;
import ru.homework.domain.Person;
import ru.homework.domain.Question;
import ru.homework.questionService.QuestionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@DisplayName("Класс Экзамен")
@ExtendWith(MockitoExtension.class)
class ExamTest {
    @Mock
    private QuestionService questionService;

    private Exam exam;
    final String nameExam = "testExam";

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
        given(questionService.getQuestions()).willReturn(questions);
        this.exam = new Exam(nameExam, questionService);
    }

    @DisplayName("Должен добавляться пользователь")
    @Test
    void addPerson() {
        Person person = new Person(new HashMap<>());
        person.setName("User");
        exam.addPerson(person);
        int size = 1;
        assertEquals(size, exam.getPersons().size());
    }

    @DisplayName("Должны выводиться опции ответов")
    @Test
    void getAnswersByIdQuestion() {
        assertNotNull(exam.getAnswersByIdQuestion(id_2));
    }

    @DisplayName("Должен выводиться ответ")
    @Test
    void getRightAnswerById() {
        assertEquals(rightAnswerId_2, exam.getRightAnswerById(id_2));
    }

    @DisplayName("Должен выводится список вопросов")
    @Test
    void getQuestions() {
        int size = 3;
        assertEquals(size, exam.getQuestions().size());
    }

    @DisplayName("Должно выводится имя экзамена")
    @Test
    void getNameExam() {
        assertEquals(this.nameExam, this.exam.getNameExam());
    }
}
