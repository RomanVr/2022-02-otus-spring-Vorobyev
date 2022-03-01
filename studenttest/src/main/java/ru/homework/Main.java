package ru.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        OutPutQuestions printQuestions = context.getBean(OutPutQuestions.class);
        printQuestions.outputQuestions();
    }
}
