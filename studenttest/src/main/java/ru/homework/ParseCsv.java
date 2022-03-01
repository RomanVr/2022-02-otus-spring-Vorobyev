package ru.homework;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Getter
@AllArgsConstructor
public class ParseCsv {
    private final String delimiter;
    private final String fileNameQuestion;

    public List<List<String>> getDataFromCsv() {
        List<List<String>> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(getPathFromFile())))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                dataList.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private String getPathFromFile() {
        URI uri = null;
        try {
            uri = getClass().getResource(String.format("/%s", getFileNameQuestion())).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return String.valueOf(Paths.get(uri));
    }
}
