package ru.homework.library.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Genre {
    private long id;
    private final String genreTitle;

    public Genre(String genreTitle) {
        this.genreTitle = genreTitle;
    }

    public Genre(long id, String genreTitle) {
        this(genreTitle);
        this.id = id;
    }
}
