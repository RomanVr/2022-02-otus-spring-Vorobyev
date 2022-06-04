package ru.homework.librarymongo.service;

import ru.homework.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();

    long insert(Author newAuthor);

    long update(Author newAuthor);

    Optional<Author> getById(long id);

    void deleteById(long id);

    Author getByNameFamily(String name, String family);
}
