package ru.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.homework.output.OutPutQuestions;

@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        OutPutQuestions outPutQuestions = context.getBean(OutPutQuestions.class);
        outPutQuestions.outPutAnswersClient();
    }
}
