package ru.homework.output;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.homework.domain.Exam;
import ru.homework.domain.Person;
import ru.homework.domain.Question;
import ru.homework.questionService.QuestionService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Component
public class TakeExamInConsole implements TakeExam {
    private final List<Question> questions;
    private final String delimiterAnswers;
    private final String separatorLine;
    private final String numberQuestion;
    private final String nameExam;

    @Autowired
    public TakeExamInConsole(
            final QuestionService questionService,
            @Value("${exam.name}") final String nameExam,
            @Value("${exam.delimiterAnswers}") final String delimiterAnswers,
            @Value("${outPut.separatorLine}") final String separatorLine,
            @Value("${exam.symbolNumber}") final String numberQuestion) {
        this.questions = questionService.getQuestions();
        this.delimiterAnswers = delimiterAnswers;
        this.nameExam = nameExam;
        this.separatorLine = separatorLine;
        this.numberQuestion = numberQuestion;
    }

    @Override
    public void runExam() {
        Exam exam = new Exam(new ArrayList<>());
        exam.setNameExam(this.nameExam);
        do {
            Person currentPerson = new Person(new HashMap<>());
            this.outputConsoleLn("Exam: " + exam.getNameExam());
            this.outConsoleSeparateLine();
            this.askName(currentPerson);
            this.outputQuestions(currentPerson);
            this.outPutAnswersClient(currentPerson);
            this.outPutResult(currentPerson);
            exam.getPersons().add(currentPerson);
        } while (this.isContinue());
    }

    @Override
    public void askName(Person person) {
        this.outputConsole("Enter your name: ");
        String readLine = getReadLine();
        person.setName(readLine);
        this.outConsoleSeparateLine();
    }

    @Override
    public void outputQuestions(Person person) {
        for (Question question : this.questions) {
            List<String> answerOptions = question.getAnswerOptions();
            this.outputConsoleLn(getNumberSymbolAddOne(question.getId()) + " - " + question.getQuestionName());
            this.outputConsoleLn("Your answer option ");
            AtomicInteger numberOption = new AtomicInteger(1);
            answerOptions.forEach(
                    option -> this.outputConsoleLn(
                            numberOption.getAndAdd(1) + " - " + option));
            this.outputConsole("Your answer - ");
            String answer = getReadLine();
            person.getAnswers().put(question.getId(), answer);
            outConsoleSeparateLine();
        }
    }

    @Override
    public void outPutAnswersClient(Person person) {
        this.outputConsoleLn("Your answers: ");
        person.getAnswers()
                .forEach(
                        (idAnswer, answer)
                                -> this.outputConsoleLn(getNumberSymbolAddOne(idAnswer) + " - " + answer));
        this.outConsoleSeparateLine();
    }

    @Override
    public void outPutResult(Person person) {
        this.outputConsoleLn(
                "Result for "
                        + person.getName()
                        + " - "
                        + getResult(person.getAnswers())
        );
        this.outConsoleSeparateLine();
    }

    @Override
    public boolean isContinue() {
        this.outputConsole("Please enter 'exit' to end exam - ");
        String readLine = getReadLine();
        if (readLine.equals("exit")) {
            return false;
        }
        this.outputConsole("\n\n\n\n\n");
        return true;
    }

    private String getNumberSymbolAddOne(final int number) {
        return this.numberQuestion + (number + 1);
    }

    private void outConsoleSeparateLine() {
        this.outputConsoleLn(getSeparatorLine());
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
            if (arraysEqual(dataAnswers, rightAnswers)) {
                result.addAndGet(1);
            }
        });
        return result.toString();
    }

    private boolean arraysEqual(final String[] dataAnswers, final String[] rightAnswer) {
        if (dataAnswers.length != rightAnswer.length) return false;
        Arrays.sort(dataAnswers);
        Arrays.sort(rightAnswer);
        for (int i = 0; i < dataAnswers.length; i += 1) {
            if (!Objects.equals(dataAnswers[i], rightAnswer[i])) return false;
        }
        return true;
    }

    private String getReadLine() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private void outputConsole(String str) {
        System.out.print(str);
    }

    private void outputConsoleLn(String str) {
        System.out.println(str);
    }
}
