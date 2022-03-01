package ru.homework;

import lombok.Getter;

import java.util.*;

@Getter
public class ConvertToMap {
    private final ParseCsv parseCsv;
    private Map<String, List<String>> questionsAnswers;

    public ConvertToMap(ParseCsv parseCsv) {
        this.parseCsv = parseCsv;
    }

    public Iterator<String> getIteratorQuestions() {
        if (this.questionsAnswers == null) {
            this.questionsAnswers = getMapQuestions();
        }
        Set<String> questions = questionsAnswers.keySet();
        return questions.iterator();
    }

    private Map<String, List<String>> getMapQuestions () {
        Map<String, List<String>> mapQuestions = new HashMap<>();

        List<List<String>> questions = parseCsv.getDataFromCsv();
        questions.forEach(lineData -> {
            List<String> answers = null;
            if (lineData.size() > 1) {
                answers = lineData.subList(1, lineData.size());
            }
            mapQuestions.put(lineData.get(0), answers);
        });
        return mapQuestions;
    }
}
