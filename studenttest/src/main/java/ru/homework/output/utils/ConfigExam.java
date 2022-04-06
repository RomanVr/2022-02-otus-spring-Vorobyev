package ru.homework.output.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "exam")
@Component
public class ConfigExam {
    private String delimiterAnswers;
    private String separatorLine;
    private String numberQuestion;
    private String nameExam;
    private String local;
}
