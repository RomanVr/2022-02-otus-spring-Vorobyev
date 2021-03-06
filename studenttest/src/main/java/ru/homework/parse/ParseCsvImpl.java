package ru.homework.parse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Getter
@Setter
@ConfigurationProperties(prefix = "parse")
@Component
public class ParseCsvImpl implements ParseCsv {
    private String delimiter;
    private String fileNameQuestion;
    private String fileNameExt;

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
        String path = String.format("/%s%s%s", getFileNameQuestion(), Locale.getDefault(), getFileNameExt());
        return path;
    }
}
