package ru.homework.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class Person {
    private String name;
    private final Map<Integer, String> answers;

    public void addAnswer(Integer idAnswer, String answer) {
        this.answers.put(idAnswer, answer);
    }
}
