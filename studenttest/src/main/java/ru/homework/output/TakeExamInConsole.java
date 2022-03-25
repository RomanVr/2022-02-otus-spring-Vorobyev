package ru.homework.output;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.homework.domain.Exam;
import ru.homework.domain.Person;
import ru.homework.domain.Question;
import ru.homework.ioService.IOService;
import ru.homework.output.utils.ResultService;
import ru.homework.questionService.QuestionService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@ConfigurationProperties(prefix = "exam")
@Component
public class TakeExamInConsole implements TakeExam {
    private final List<Question> questions;
    private final IOService ioService;
    private final MessageSource ms;
    private String delimiterAnswers;
    private String separatorLine;
    private String numberQuestion;
    private String nameExam;

    public TakeExamInConsole(
            final QuestionService questionService,
            final IOService ioService,
            final MessageSource messageSource) {
        this.questions = questionService.getQuestions();
        this.ioService = ioService;
        this.ms = messageSource;
    }

    @Override
    public void runExam() {
        Exam exam = new Exam(new ArrayList<>());
        exam.setNameExam(this.nameExam);
        Person currentPerson;
        do {
            this.ioService.outputString(getMessage("strings.nameexam", new String[]{exam.getNameExam()}));
            this.outConsoleSeparateLine();
            String name = this.askName();
            currentPerson = new Person(new HashMap<>(), name);
            this.outputQuestions(currentPerson);
            this.ioService.outputString(getMessage("strings.answers", null));
            this.outPutAnswersClient(currentPerson);
            this.outConsoleSeparateLine();
            this.outPutResult(currentPerson);
            this.outConsoleSeparateLine();
            exam.getPersons().add(currentPerson);
        } while (this.isContinue());
    }

    @Override
    public String askName() {
        String name = this.ioService.readWithPrompt(getMessage("strings.nameperson", null));
        this.outConsoleSeparateLine();
        return name;
    }

    @Override
    public void outputQuestions(Person person) {
        for (Question question : this.questions) {
            List<String> answerOptions = question.getAnswerOptions();
            this.ioService.outputString(getNumberSymbolAddOne(question.getId()) + " - " + question.getQuestionName());
            this.ioService.outputString(getMessage("strings.answeroption", null));
            AtomicInteger numberOption = new AtomicInteger(1);
            answerOptions.forEach(
                    option -> this.ioService.outputString(
                            numberOption.getAndAdd(1) + " - " + option));
            String answer = this.ioService.readWithPrompt(getMessage("strings.youranswer", null));
            person.getAnswers().put(question.getId(), answer);
            this.outConsoleSeparateLine();
        }
    }

    @Override
    public void outPutAnswersClient(Person person) {
        person.getAnswers()
                .forEach(
                        (idAnswer, answer)
                                -> this.ioService.outputString(getNumberSymbolAddOne(idAnswer) + " - " + answer));
    }

    @Override
    public void outPutResult(Person person) {
        this.ioService.outputString(
                getMessage("strings.result", null)
                        + person.getName()
                        + " - "
                        + getResult(person.getAnswers())
        );
    }

    @Override
    public boolean isContinue() {
        String readLine = this.ioService.readWithPrompt(getMessage("strings.exit", null));
        if (readLine.equals("exit")) {
            return false;
        }
        this.ioService.outputString("\n\n\n\n\n");
        return true;
    }

    private String getResult(Map<Integer, String> answers) {
        Set<Integer> idAnswers = answers.keySet();
        AtomicInteger result = new AtomicInteger(0);
        idAnswers.forEach(id -> {
            String[] dataAnswers = answers.get(id).split(delimiterAnswers);
            String[] rightAnswers = questions
                    .get(id)
                    .getRightAnswers()
                    .split(this.delimiterAnswers);
            if (ResultService.arraysEqual(dataAnswers, rightAnswers)) {
                result.addAndGet(1);
            }
        });
        return result.toString();
    }

    private String getNumberSymbolAddOne(final int number) {
        return this.numberQuestion + (number + 1);
    }

    private void outConsoleSeparateLine() {
        this.ioService.outputString(getSeparatorLine());
    }

    private String getMessage(String code, Object[] args) {
        return this.ms.getMessage(code, args, Locale.getDefault());
    }
}
