package ru.homework.service;

import java.util.List;
import java.util.Map;

public interface ExamService {
    void startExam();

    void setPersonName(final String userName);

    void addAnswer(Integer idQuestion, String answer);

    void savePerson();

    Map<Integer, String> getQuestions();

    Map<Integer, String> getAnswersPerson();

    List<String> getAnswersByIdQuestion(int idQuestions);

    String getResult();

    String getNameExam();
}
