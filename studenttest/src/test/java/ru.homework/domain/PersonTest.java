package ru.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс Пользователя")
class PersonTest {

    private Person person;
    private final String namePerson = "User";

    @BeforeEach
    void setUp() {
        this.person = new Person(new HashMap<>(), this.namePerson);
    }

    @DisplayName("Должен создаваться answers Map<Integer, String>")
    @Test
    void getAnswers() {
        assertNotNull(person.getAnswers());
    }

    @DisplayName("Должно выводиться имя пользователя")
    @Test
    void getName() {
        assertEquals(namePerson, person.getName());
    }
}
