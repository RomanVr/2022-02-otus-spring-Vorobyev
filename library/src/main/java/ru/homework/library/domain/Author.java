package ru.homework.library.domain;

import lombok.*;

import java.sql.Date;

@Data
public class Author {
    private long id;
    private final String name;
    private final String family;
    private final Date dateOfBirth;
    private final String gender;

    public Author(long id, String name, String family, Date dateOfBirth, String gender) {
        this(name, family, dateOfBirth, gender);
        this.id = id;
    }

    public Author(String name, String family, Date dateOfBirth, String gender) {
        this.name = name;
        this.family = family;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
