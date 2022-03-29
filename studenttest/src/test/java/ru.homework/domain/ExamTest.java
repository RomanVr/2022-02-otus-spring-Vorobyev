package ru.homework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс Экзамен")
class ExamTest {

    private Exam exam;
    private final String nameExam = "test for student";

    @BeforeEach
    void setUp() {
        this.exam = new Exam(new ArrayList<>(), this.nameExam);
    }

    @DisplayName("Должен создаваться список участников")
    @Test
    void getPersons() {
        assertNotNull(exam.getPersons());
    }

    @DisplayName("Должен добавляться пользователь")
    @Test
    void addPerson() {
        String namePerson = "User";
        Person person = new Person(new HashMap<>(), namePerson);
        exam.getPersons().add(person);
        int size = 1;
        assertEquals(size, exam.getPersons().size());
    }

    @DisplayName("Должно выводится имя экзамена")
    @Test
    void getNameExam() {
        assertEquals(this.nameExam, this.exam.getNameExam());
    }
}
