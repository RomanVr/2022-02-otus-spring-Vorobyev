package ru.homework.examService;

import java.util.List;
import java.util.Map;

public interface ExamService {
    void getNewPerson();

    void setPersonName(final String userName);

    void addAnswer(final Integer idQuestion, final String answer);

    void savePerson();

    Map<Integer, String> getQuestions();

    Map<Integer, String> getAnswersPerson();

    List<String> getAnswersByIdQuestion(final int idQuestions);

    String getResult();

    String getNameExam();

    String getNamePerson();
}
