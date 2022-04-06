package ru.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationTestStudent {
    public static void main(String[] args) {
        /*ApplicationContext context = */
        SpringApplication.run(ApplicationTestStudent.class, args);
/*        TakeExam takeExam = context.getBean(TakeExamInConsole.class);
        takeExam.runExam();*/
    }
}
