package ru.homework.output;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.homework.domain.Exam;
import ru.homework.domain.Person;
import ru.homework.domain.Question;
import ru.homework.ioService.IOService;
import ru.homework.output.utils.ConfigExam;
import ru.homework.output.utils.ResultService;
import ru.homework.questionService.QuestionService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@RequiredArgsConstructor

@ShellComponent
public class TakeExamShell implements TakeExam {
    private final QuestionService questionService;
    private final IOService ioService;
    private final MessageSource ms;
    private final ConfigExam configExam;

    @ShellMethod(value = "Start command", key = {"start", "s", "run", "r"})
    @Override
    public void runExam() {
        Exam exam = new Exam(new ArrayList<>(), this.configExam.getNameExam());
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
        for (Question question : this.questionService.getQuestions()) {
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
            String[] dataAnswers = answers.get(id).split(this.configExam.getDelimiterAnswers());
            String[] rightAnswers = this.questionService.getQuestions()
                    .get(id)
                    .getRightAnswers()
                    .split(this.configExam.getDelimiterAnswers());
            if (ResultService.arraysEqual(dataAnswers, rightAnswers)) {
                result.addAndGet(1);
            }
        });
        return result.toString();
    }

    private String getNumberSymbolAddOne(final int number) {
        return this.configExam.getNumberQuestion() + (number + 1);
    }

    private void outConsoleSeparateLine() {
        this.ioService.outputString(configExam.getSeparatorLine());
    }

    private String getMessage(String code, Object[] args) {
        /*FIXME Локаль можно и позволить менять.
           В общем не критично, но полезно.*/
        return this.ms.getMessage(code, args, Locale.getDefault());
    }
}
