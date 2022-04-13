package ru.homework.library.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String bookTitle;
    private final Author author;
    private final Genre genre;
}
