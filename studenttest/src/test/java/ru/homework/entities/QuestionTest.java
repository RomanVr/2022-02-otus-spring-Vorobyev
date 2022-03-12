package ru.homework.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.homework.domain.Question;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс 'Вопрос'")
class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        this.question = new Question();
    }

    @DisplayName("Должно выводиться id вопроса")
    @Test
    void getId() {
        int id = 1;
        this.question.setId(id);
        assertEquals(id, this.question.getId());
    }

    @DisplayName("Должен выводиться вопрос")
    @Test
    void getQuestionName() {
        String questionName = "who im i?";
        question.setQuestionName(questionName);
        assertEquals(questionName, question.getQuestionName());
    }

    @DisplayName("Должен выводиться список вариантов ответа")
    @Test
    void getAnswerOptions() {
        String[] strOptions = {"i'm not here", "i am there"};
        question.setAnswerOptions(Arrays.asList(strOptions));
        assertNotNull(question.getAnswerOptions());
    }

    @DisplayName("Должен выводиться правильный ответ")
    @Test
    void getRightAnswers() {
        String rightAnswer = "1,2";
        question.setRightAnswers(rightAnswer);
        assertEquals(rightAnswer, question.getRightAnswers());
    }
}
