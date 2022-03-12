package ru.homework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Component
@Scope("prototype")
public class Question {
    private int id;
    private String questionName;
    private List<String> answerOptions;
    private String rightAnswers;
}
