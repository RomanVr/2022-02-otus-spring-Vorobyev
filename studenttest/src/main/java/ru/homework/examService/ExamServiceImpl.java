package ru.homework.examService;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.homework.domain.Exam;
import ru.homework.domain.Person;
import ru.homework.domain.Question;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Service
public class ExamServiceImpl implements ExamService {
    private final Exam exam;
    private final String delimiterAnswers;
    private final ApplicationContext ctx;
    private Person person;

    @Autowired
    public ExamServiceImpl(
            final Exam exam,
            final AnnotationConfigApplicationContext applicationContext,
            @Value("${exam.delimiter}") final String delimiterAnswers) {
        this.exam = exam;
        this.delimiterAnswers = delimiterAnswers;
        this.ctx = applicationContext;
    }

    @Override
    public String getNameExam() {
        return this.exam.getNameExam();
    }

    @Override
    public void startExam() {
        this.person = this.ctx.getBean(Person.class);
    }

    @Override
    public void setPersonName(final String name) {
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
        List<Question> questionList = exam.getQuestions();
        for (Question question : questionList) {
            questions.put(question.getId(), question.getQuestionName());
        }
        return questions;
    }

    @Override
    public List<String> getAnswersByIdQuestion(final int idQuestions) {
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
            String[] dataAnswers = answersPerson.get(id).split(delimiterAnswers);
            if (isCorrect(id, dataAnswers)) {
                result.addAndGet(1);
            }
        });
        return result.toString();
    }

    private String getRightAnswerById(final int id) {
        return exam.getRightAnswerById(id);
    }

    private boolean isCorrect(final int id, final String[] dataAnswers) {
        String[] rightAnswer = getRightAnswerById(id).split(delimiterAnswers);
        return arrayEqual(dataAnswers, rightAnswer);
    }

    private boolean arrayEqual(final String[] dataAnswers, final String[] rightAnswer) {
        if (dataAnswers.length != rightAnswer.length) return false;
        Arrays.sort(dataAnswers);
        Arrays.sort(rightAnswer);
        for (int i = 0; i < dataAnswers.length; i += 1) {
            if (!Objects.equals(dataAnswers[i], rightAnswer[i])) return false;
        }
        return true;
    }
}
