package ru.homework.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.homework.convert.ConvertToMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class OutPutQuestions {
    private final ConvertToMap convertToMap;
    private final String separatorLine;

    public List<String> outputQuestions() {
        Map<String, List<String>> questionsAnswers = convertToMap.getQuestionsAnswers();
        Set<String> questions = questionsAnswers.keySet();
        List<String> answersClient = new ArrayList<>();
        for (String question : questions) {
            List<String> answers = questionsAnswers.get(question);
            System.out.println(question);
            AtomicInteger i = new AtomicInteger(1);
            System.out.println("Your answer option ");
            answers.forEach(answer -> System.out.println(i.getAndIncrement() + " - " + answer));
            System.out.print("Your answer - ");
            try {
                String answer = getReadLine();
                answersClient.add(answer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(getSeparatorLine());
        }
        return answersClient;
    }

    public void outPutAnswersClient() {
        List<String> answers = outputQuestions();
        System.out.println("Your answers: ");
        answers.forEach(System.out::println);
    }

    private String getReadLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
