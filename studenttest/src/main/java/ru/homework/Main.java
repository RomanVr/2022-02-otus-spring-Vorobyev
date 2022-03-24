package ru.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.homework.output.TakeExam;
import ru.homework.output.TakeExamInConsole;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        TakeExam takeExam = context.getBean(TakeExamInConsole.class);
        takeExam.runExam();
    }
}
