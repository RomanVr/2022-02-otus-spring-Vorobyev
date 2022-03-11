package ru.homework.examService;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.homework.configure.ApplicationContextHolder;
import ru.homework.entities.Exam;
import ru.homework.entities.Person;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Service
public class ExamServiceImpl implements ExamService {
    private final Exam exam;
    private final String DELIMITER_ANSWERS;
    private final ApplicationContext ctx;
    private Person person;

    @Autowired
    public ExamServiceImpl(
            final Exam exam,
            @Value("${exam.delimiter}") final String delimiterAnswers) {
        this.exam = exam;
        this.DELIMITER_ANSWERS = delimiterAnswers;
        this.ctx = ApplicationContextHolder.getApplicationContext();
    }

    @Override
    public void startExam() {
        this.person = this.ctx.getBean(Person.class);
    }

    @Override
    public void setPersonName(String name) {
        this.person.setName(name);
    }

    @Override
    public String getNamePerson() {
        return person.getName();
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
    public Map<Integer, String> getQuestions() {
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
        Set<Integer> idAnswers = answersPerson.keySet();
        AtomicInteger result = new AtomicInteger(0);
        idAnswers.forEach(id -> {
            String[] dataAnswers = answersPerson.get(id).split(DELIMITER_ANSWERS);
            if (isCorrect(id, dataAnswers)) {
                result.addAndGet(1);
            }
        });
        return result.toString();
    }

    private boolean isCorrect(final int id, final String[] dataAnswers) {
        String[] rightAnswer = getRightAnswerById(id).split(DELIMITER_ANSWERS);
        return arrayEqual(dataAnswers, rightAnswer);
    }

    private boolean arrayEqual(String[] dataAnswers, String[] rightAnswer) {
        if (dataAnswers.length != rightAnswer.length) return false;
        Arrays.sort(dataAnswers);
        Arrays.sort(rightAnswer);
        for (int i = 0; i < dataAnswers.length; i += 1) {
            if (!Objects.equals(dataAnswers[i], rightAnswer[i])) return false;
        }
        return true;
    }

    private String getRightAnswerById(final int id) {
        return exam.getRightAnswerById(id);
    }

    @Override
    public String getNameExam() {
        return exam.getNameExam();
    }
}

