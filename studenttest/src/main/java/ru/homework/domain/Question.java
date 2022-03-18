package ru.homework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Question {
    private int id;
    private String questionName;
    private List<String> answerOptions;
    private String rightAnswers;
}
