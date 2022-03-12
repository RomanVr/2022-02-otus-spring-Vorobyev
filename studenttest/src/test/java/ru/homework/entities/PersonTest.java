package ru.homework.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.homework.domain.Person;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Пользователя")
class PersonTest {

    private final String namePerson = "User";
    private Person person;
    @BeforeEach
    void setUp() {
        this.person = new Person(new HashMap<>());
    }

    @DisplayName("Должен добавляться ответ пользователя")
    @Test
    void addAnswer() {
        String answer = "213123";
        int id = 1;
        person.addAnswer(id, answer);
        assertEquals(answer, person.getAnswers().get(id));
    }

    @DisplayName("Должен создаваться answers Map<Integer, String>")
    @Test
    void getAnswers() {
        assertNotNull(person.getAnswers());
    }

    @DisplayName("Должно выводиться имя пользователя")
    @Test
    void getName() {
        this.person.setName(this.namePerson);
        assertEquals(this.namePerson, person.getName());
    }
}
