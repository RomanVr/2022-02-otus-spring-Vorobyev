package ru.homework.library.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Book {
    private long id;
    private final String bookTitle;
    private final String preview;
    private final Author author;
    private final Genre genre;

    public Book(long id, String bookTitle, String preview, Author author, Genre genre) {
        this(bookTitle, preview, author, genre);
        this.id = id;
    }

    public Book(String bookTitle, String preview, Author author, Genre genre) {
        this.bookTitle = bookTitle;
        this.preview = preview;
        this.author = author;
        this.genre = genre;
    }
}
