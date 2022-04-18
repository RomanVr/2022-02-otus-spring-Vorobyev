package ru.homework.library.domain;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Author {
    private long id;
    private final String name;
    private final String family;
    private final Date dateOfBirth;
    private final String gender;
    private final List<Book> bookList;

    public Author(String name, String family, Date dateOfBirth, String gender) {
        this.name = name;
        this.family = family;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bookList = new ArrayList<>();
    }

    public Author(long id, String name, String family, Date dateOfBirth, String gender) {
        this(name, family, dateOfBirth, gender);
        this.id = id;
    }

    @Override
    public String toString() {
        long id1 = this.getId();
        StringBuilder authorStringBuilder = new StringBuilder("Author(id=" + id1 + ", name=" + this.getName() + ", family=" + this.getFamily() + ", dateOfBirth=" + this.getDateOfBirth() + ", gender=" + this.getGender() + ")");
        authorStringBuilder.append("\n");
        for (Book book : this.bookList) {
            authorStringBuilder.append("\t");
            authorStringBuilder.append(book.toString());
            authorStringBuilder.append("\n");
        }
        return authorStringBuilder.toString();
    }
}
