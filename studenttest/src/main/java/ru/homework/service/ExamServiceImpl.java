package ru.homework.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.entyties.Exam;
import ru.homework.entyties.Person;
import ru.homework.entyties.Question;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Getter
public class ExamServiceImpl implements ExamService {
    private final Exam exam;
    private Person person;
    private static final String DELIMITER_ANSWERS = ",";

    @Autowired
    public ExamServiceImpl (final Exam exam) {
        this.exam = exam;
    }

    @Override
    public void startExam(){
        this.person = new Person(new HashMap<>());
    }

    @Override
    public void setPersonName(String name) {
        this.person.setName(name);
    }

    @Override
    public void addAnswer(Integer idQuestion, String answer) {
        this.person.addAnswer(idQuestion, answer);
    }

    @Override
    public void savePerson() {
        exam.addPerson(this.person);
    }

    @Override
    public Map<Integer, String> getQuestions(){
        Map<Integer, String> questions = new HashMap<>();
        exam.getQuestions().forEach(question -> questions.put(question.getId(), question.getQuestionName()));
        return questions;
    }

    @Override
    public List<String> getAnswersByIdQuestion(int idQuestions) {
        return exam.getAnswersByIdQuestion(idQuestions);
    }

    @Override
    public Map<Integer, String> getAnswersPerson() {
        return this.person.getAnswers();
    }

    @Override
    public String getResult() {
        Map<Integer, String> answersPerson = getAnswersPerson();
        Set<Integer> idQuestions = answersPerson.keySet();
        AtomicInteger result = new AtomicInteger(0);
        idQuestions.forEach(id -> {
            String[] dataAnswers = answersPerson.get(id).split(DELIMITER_ANSWERS);
            if(isCorrect(id, dataAnswers)) {
                result.addAndGet(1);
            }
        });
        return result.toString();
    }

    private boolean isCorrect(final int id, final String[] dataAnswers) {
        Map<Integer, String> questions = this.getQuestions();
        String[] rightAnswer = getRightAnswerById(id).split(DELIMITER_ANSWERS);
        return arrayEqual(dataAnswers, rightAnswer);
    }

    private boolean arrayEqual(String[] dataAnswers, String[] rightAnswer) {
        if(dataAnswers.length != rightAnswer.length) return false;
        Arrays.sort(dataAnswers);
        Arrays.sort(rightAnswer);
        for(int i = 0; i < dataAnswers.length; i += 1) {
            if(!Objects.equals(dataAnswers[i], rightAnswer[i])) return false;
        }
        return true;
    }

    private String getRightAnswerById(final int id){
        return exam.getRightAnswerById(id);
    }

    @Override
    public String getNameExam() {
        return exam.getNameExam();
    }
}
