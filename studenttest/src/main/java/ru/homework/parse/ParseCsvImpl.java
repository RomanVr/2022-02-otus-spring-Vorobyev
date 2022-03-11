package ru.homework.parse;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Component
public class ParseCsvImpl implements ParseCsv {
    private final String delimiter;
    private final String fileNameQuestion;

    public ParseCsvImpl(
            @Value("${parse.delimiter}") final String delimiter,
            @Value("${parse.fileNameQuestion}") final String fileNameQuestion
    ) {
        this.delimiter = delimiter;
        this.fileNameQuestion = fileNameQuestion;
    }

    @Override
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
