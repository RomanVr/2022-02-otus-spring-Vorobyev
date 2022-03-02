package ru.homework.convert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.homework.parse.ParseCsv;

import java.util.*;

@Getter
@AllArgsConstructor
public class ConvertToMap {
    private final ParseCsv parseCsv;

    public Map<String, List<String>> getQuestionsAnswers () {
        Map<String, List<String>> questionsAnswers = new HashMap<>();

        List<List<String>> dataFromCsv = parseCsv.getDataFromCsv();
        dataFromCsv.forEach(lineData -> {
            List<String> answers = null;
            if (lineData.size() > 1) {
                answers = lineData.subList(1, lineData.size());
            }
            questionsAnswers.put(lineData.get(0), answers);
        });
        return questionsAnswers;
    }
}
