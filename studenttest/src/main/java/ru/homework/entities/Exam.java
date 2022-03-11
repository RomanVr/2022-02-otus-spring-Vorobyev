package ru.homework.entities;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.homework.questionService.QuestionService;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class Exam {
    private final String nameExam;
    private final QuestionService questionService;
    private final List<Person> persons;
    private List<Question> questions;

    @Autowired
    public Exam(
            @Value("${exam.name}") String nameExam,
            QuestionService questionService
    ) {
        this.nameExam = nameExam;
        this.questionService = questionService;

        this.persons = new ArrayList<>();
        this.setQuestions();
    }

    public void addPerson(final Person person) {
        this.persons.add(person);
    }

    public List<String> getAnswersByIdQuestion(final int id) {
        Question question = findQuestionById(id);
        return question.getAnswerOptions();
    }

    public String getRightAnswerById(final int id) {
        Question question = findQuestionById(id);
        return question.getRightAnswers();
    }

    private Question findQuestionById(int id) {
        Question findQuestion = null;
        for (Question question : this.questions) {
            if (question.getId() == id) {
                findQuestion = question;
                break;
            }
        }
        return findQuestion;
    }

    private void setQuestions() {
        this.questions = questionService.getQuestions();
    }
}
