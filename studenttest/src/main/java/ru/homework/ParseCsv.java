package ru.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

@Getter
@AllArgsConstructor
public class ParseCsv {
    private final String delimiter;
    private final String fileQuestion;

    public Map<String, List<String>> getMapQuestions () throws URISyntaxException {
        Map<String, List<String>> mapQuestions = new HashMap<>();
        List<List<String>> questions = new ArrayList<>();
        getDataFromCsv(questions);
        questions.forEach(lineData -> {
            List<String> answers = null; 
            if (lineData.size() > 1) {
                answers = lineData.subList(1, lineData.size() - 1);
            }
            mapQuestions.put(lineData.get(0), answers);
        });
        return mapQuestions;
    }

    private void getDataFromCsv(List<List<String>> questions) throws URISyntaxException {
        URI uri = getClass().getResource(String.format("/%s", getFileQuestion())).toURI();
        System.out.println(Paths.get(uri));
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(Paths.get(uri)))))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                questions.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
