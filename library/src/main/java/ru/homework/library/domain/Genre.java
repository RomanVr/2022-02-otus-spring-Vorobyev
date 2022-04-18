package ru.homework.library.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Genre {
    private long id;
    private final String genreTitle;
    private final List<Book> bookList;

    public Genre(String genreTitle) {
        this.genreTitle = genreTitle;
        this.bookList = new ArrayList<>();
    }

    public Genre(long id, String genreTitle) {
        this(genreTitle);
        this.id = id;
    }

    @Override
    public String toString() {
        long id1 = this.getId();
        StringBuilder genreBuilder = new StringBuilder("Genre(id=" + id1 + ", genreTitle=" + this.getGenreTitle() + ")");
        genreBuilder.append("\n");
        for (Book book : this.bookList) {
            genreBuilder.append("\t");
            genreBuilder.append(book.toString());
            genreBuilder.append("\n");
        }
        return genreBuilder.toString();
    }

}
