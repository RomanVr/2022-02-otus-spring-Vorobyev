package ru.homework.output;

import ru.homework.domain.Person;

public interface TakeExam {
    boolean isContinue();

    void runExam();

    void askName(Person person);

    void outputQuestions(Person person);

    void outPutAnswersClient(Person person);

    void outPutResult(Person person);
}
