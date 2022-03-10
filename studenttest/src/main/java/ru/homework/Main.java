package ru.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.homework.output.TakeExam;
import ru.homework.output.TakeExamInConsole;

@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TakeExam takeExam = context.getBean(TakeExamInConsole.class);

        do {
            takeExam.startExam();
            takeExam.askName();
            takeExam.outputQuestions();
            takeExam.outPutAnswersClient();
            takeExam.outPutResult();
        } while (takeExam.isContinue());
    }
}
