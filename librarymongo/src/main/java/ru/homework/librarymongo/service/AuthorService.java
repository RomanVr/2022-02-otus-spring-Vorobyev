package ru.homework.librarymongo.service;

import ru.homework.librarymongo.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAll();

    String insert(Author newAuthor);

    String update(Author newAuthor);

    Optional<Author> getById(String id);

    void deleteById(String id);

    Author getByNameFamily(String name, String family);
}
