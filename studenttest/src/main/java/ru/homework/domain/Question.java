package ru.homework.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Question {
    private final int id;
    private final String questionName;
    private final List<String> answerOptions;
    private final String rightAnswers;
}
