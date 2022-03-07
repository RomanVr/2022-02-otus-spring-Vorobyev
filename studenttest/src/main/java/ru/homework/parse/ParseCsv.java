package ru.homework.parse;

import java.util.List;

public interface ParseCsv {
    default List<List<String>> getDataFromCsv() {
        return null;
    }
}
