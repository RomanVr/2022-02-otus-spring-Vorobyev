package ru.homework.output;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.homework.examService.ExamService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Component
public class TakeExamInConsole implements TakeExam {
    private final ExamService examService;
    private final String separatorLine;

    @Autowired
    public TakeExamInConsole(
            ExamService examService,
            @Value("${outPut.separatorLine}") String separatorLine) {
        this.examService = examService;
        this.separatorLine = separatorLine;
    }

    @Override
    public void startExam(){
        examService.startExam();
        System.out.println("Exam: " + examService.getNameExam());
        outConsoleSeparateLine();
    }

    @Override
    public void askName(){
        System.out.print("Enter your name: ");
        String readLine = getReadLine();
        examService.setPersonName(readLine);
        outConsoleSeparateLine();
    }

    @Override
    public void outputQuestions() {
        Map<Integer, String> questions = examService.getQuestions();
        Set<Integer> idQuestion = questions.keySet();
        for (Integer id : idQuestion) {
            List<String> answerOptions = examService.getAnswersByIdQuestion(id);
            System.out.println(getNumberSymbolAddOne(id) + " - " + questions.get(id));
            System.out.println("Your answer option ");
            AtomicInteger numberOption = new AtomicInteger(1);
            answerOptions.forEach(
                    option -> System.out.println(
                            numberOption.getAndAdd(1) + " - " + option));
            System.out.print("Your answer - ");
            String answer = getReadLine();
            examService.addAnswer(id, answer);
            outConsoleSeparateLine();
        }
    }

    @Override
    public void outPutAnswersClient() {
        Map<Integer, String> answers = examService.getAnswersPerson();
        Set<Integer> idAnswers = answers.keySet();
        System.out.println("Your answers: ");
        idAnswers.forEach(idAnswer -> System.out.println(getNumberSymbolAddOne(idAnswer) + " - " + answers.get(idAnswer)));
        outConsoleSeparateLine();
    }

    @Override
    public void outPutResult() {
        System.out.println("Result for " + examService.getNamePerson() + " - " + examService.getResult());
        outConsoleSeparateLine();
        examService.savePerson();
    }

    @Override
    public boolean isContinue(){
        System.out.print("Please enter 'exit' to end exam - ");
        String readLine = getReadLine();
        if(readLine.equals("exit")) {
            return false;
        }
        System.out.println("\n\n\n\n\n");
        return true;
    }

    private String getNumberSymbolAddOne(final int number){
        return "№_" + (number+1);
    }

    private void outConsoleSeparateLine(){
        System.out.println(getSeparatorLine());
    }
    private String getReadLine() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
