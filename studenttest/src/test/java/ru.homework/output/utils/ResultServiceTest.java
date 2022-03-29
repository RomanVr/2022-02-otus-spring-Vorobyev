package ru.homework.output.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс проверки результатов")
class ResultServiceTest {

    @DisplayName("Должны совпадать ответы")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3", "2,1,3", "3,1,2"})
    void arrayEqualToTrue(String answer) {
        String[] answers = answer.split(",");
        String[] resultRight = {"1", "2", "3"};
        assertTrue(ResultService.arraysEqual(resultRight, answers));
    }

    @DisplayName("Ответы не должны совпадать")
    @ParameterizedTest
    @ValueSource(strings = {"1,2", "1,1,3", "3"})
    void arrayEqualToFalse(String answer) {
        String[] answers = answer.split(",");
        String[] resultRight = {"1", "2", "3"};
        assertFalse(ResultService.arraysEqual(resultRight, answers));
    }
}