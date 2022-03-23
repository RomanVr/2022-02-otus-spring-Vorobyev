package ru.homework.output;

import ru.homework.domain.Person;

public interface TakeExam {
    boolean isContinue();

    void runExam();

    String askName();

    void outputQuestions(Person person);

    void outPutAnswersClient(Person person);

    void outPutResult(Person person);
}
