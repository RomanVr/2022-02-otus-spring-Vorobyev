package ru.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ParseCsv parseCsv = context.getBean(ParseCsv.class);
        System.out.println(parseCsv.getFileQuestion());
        try {
            parseCsv.getMapQuestions();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
