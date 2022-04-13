package ru.homework.library.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
public class Author {
    private final long id;
    private final String name;
    private final String family;
    private final Date dateOfBirth;
    private final String gender;
}
