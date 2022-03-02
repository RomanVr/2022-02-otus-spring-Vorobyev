package ru.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.homework.output.OutPutQuestions;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        OutPutQuestions outPutQuestions = context.getBean(OutPutQuestions.class);
        outPutQuestions.outPutAnswersClient();
    }
}
