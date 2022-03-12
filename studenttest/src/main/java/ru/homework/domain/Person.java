package ru.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@Component
@Scope("prototype")
public class Person {
    private final Map<Integer, String> answers;
    private String name;

    public void addAnswer(final Integer idAnswer, final String answer) {
        this.answers.put(idAnswer, answer);
    }
}
