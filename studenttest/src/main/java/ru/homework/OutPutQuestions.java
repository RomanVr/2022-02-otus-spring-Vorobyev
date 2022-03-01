package ru.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class OutPutQuestions {
    private final ConvertToMap convertToMap;
    private final String separatorLine;

    public void outputQuestions() {
        Iterator<String> iteratorQuestions = convertToMap.getIteratorQuestions();
        while (iteratorQuestions.hasNext()) {
            String question = iteratorQuestions.next();
            List<String> answers = convertToMap.getQuestionsAnswers().get(question);
            System.out.println(question);
            AtomicInteger i = new AtomicInteger(1);
            System.out.println("Your answer option ");
            answers.forEach(answer -> System.out.println(i.getAndIncrement() + " - " + answer));
            System.out.print("Your answer - ");
            try {
                getReadLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(getSeparatorLine());
        }
    }

    private String getReadLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
}
