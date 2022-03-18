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
@ExtendWith(MockitoExtension.class)
class ExamTest {

    private Exam exam;

    @BeforeEach
    void setUp() {
        this.exam = new Exam(new ArrayList<>());
    }

    @DisplayName("Должен создаваться список участников")
    @Test
    void getPersons() {
        assertNotNull(exam.getPersons());
    }

    @DisplayName("Должен добавляться пользователь")
    @Test
    void addPerson() {
        Person person = new Person(new HashMap<>());
        person.setName("User");
        exam.getPersons().add(person);
        int size = 1;
        assertEquals(size, exam.getPersons().size());
    }

    @DisplayName("Должно выводится имя экзамена")
    @Test
    void getNameExam() {
        String nameExam = "test for student";
        exam.setNameExam(nameExam);
        assertEquals(nameExam, this.exam.getNameExam());
    }
}
