package ru.homework.convert;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.homework.parse.ParseCsv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class ConvertToMapImpl implements ConvertToMap{
    private final ParseCsv parseCsv;

    @Autowired
    public ConvertToMapImpl(ParseCsv parseCsv) {
        this.parseCsv = parseCsv;
    }

    @Override
    public Map<String, List<String>> getQuestionsAnswers() {
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
