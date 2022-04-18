package ru.homework.library.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Book {
    private long id;
    private final String bookTitle;
    private final String preview;

    public Book(String bookTitle, String preview) {
        this.bookTitle = bookTitle;
        this.preview = preview;
    }

    public Book(long id, String bookTitle, String preview) {
        this(bookTitle, preview);
        this.id = id;
    }
}
