package ru.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс 'Вопрос'")
class QuestionTest {

    private Question question;
    private final int id = 1;
    private final String questionName = "who im i?";
    private final List<String> strOptions = List.of("i'm not here", "i am there");
    private final String rightAnswer = "1,2";

    @BeforeEach
    void setUp() {
        this.question = new Question(this.id, this.questionName, this.strOptions, rightAnswer);
    }

    @DisplayName("Должно выводиться id вопроса")
    @Test
    void getId() {
        assertEquals(this.id, this.question.getId());
    }

    @DisplayName("Должен выводиться вопрос")
    @Test
    void getQuestionName() {
        assertEquals(this.questionName, this.question.getQuestionName());
    }

    @DisplayName("Должен выводиться список вариантов ответа")
    @Test
    void getAnswerOptions() {
        assertNotNull(question.getAnswerOptions());
    }

    @DisplayName("Должен выводиться правильный ответ")
    @Test
    void getRightAnswers() {
        assertEquals(rightAnswer, question.getRightAnswers());
    }
}
