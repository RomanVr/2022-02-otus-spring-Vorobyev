package ru.homework.output;

public interface TakeExam {
    boolean isContinue();

    void startExam();

    void askName();

    void outputQuestions();

    void outPutAnswersClient();

    void outPutResult();
}
