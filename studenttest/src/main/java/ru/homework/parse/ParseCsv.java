package ru.homework.parse;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.util.*;

@Getter
@AllArgsConstructor
public class ParseCsv {
    private final String delimiter;
    private final String fileNameQuestion;

    public List<List<String>> getDataFromCsv() {
        List<List<String>> dataList = new ArrayList<>();
        try (BufferedReader br =
                     new BufferedReader(
                             new InputStreamReader(
                                     Objects.requireNonNull(
                                             this.getClass().getResourceAsStream(
                                                     getPathFromFile()))))) {
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
        return String.format("/%s", getFileNameQuestion());
    }
}
