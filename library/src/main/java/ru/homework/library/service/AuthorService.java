package ru.homework.library.service;

import ru.homework.library.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    long insert(Author newAuthor);

    long update(Author newAuthor);

    Author getById(long id);

    void deleteById(long id);

    Author getByNameFamily(String name, String family);
}
