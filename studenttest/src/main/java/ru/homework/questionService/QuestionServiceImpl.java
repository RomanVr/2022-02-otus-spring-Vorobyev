package ru.homework.questionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.homework.domain.Question;
import ru.homework.parse.ParseCsv;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final List<List<String>> questionsAndAnswers;

    @Autowired
    public QuestionServiceImpl(
            final ParseCsv parseCsv) {
        this.questionsAndAnswers = parseCsv.getDataFromCsv();
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        AtomicInteger id = new AtomicInteger();
        this.questionsAndAnswers.forEach(lineData -> {
            List<String> answerOptions = null;
            String rightAnswer = "";
            if (lineData.size() > 1) {
                rightAnswer = lineData.subList(1, 2).get(0);
            }
            if (lineData.size() > 2) {
                answerOptions = lineData.subList(2, lineData.size());
            }
            int id_question = id.getAndAdd(1);
            String questionName = lineData.get(0);
            Question question = new Question(id_question, questionName, answerOptions, rightAnswer);
            questions.add(question);
        });
        return questions;
    }
}
