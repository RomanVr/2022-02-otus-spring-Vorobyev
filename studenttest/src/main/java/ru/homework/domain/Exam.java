package ru.homework.domain;

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
    private final List<Person> persons;
    private final List<Question> questions;

    @Autowired
    public Exam(
            @Value("${exam.name}") final String nameExam,
            final QuestionService questionService
    ) {
        this.nameExam = nameExam;

        this.persons = new ArrayList<>();
        this.questions = questionService.getQuestions();
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

    private Question findQuestionById(final int id) {
        Question findQuestion = null;
        for (Question question : this.questions) {
            if (question.getId() == id) {
                findQuestion = question;
                break;
            }
        }
        return findQuestion;
    }
}
