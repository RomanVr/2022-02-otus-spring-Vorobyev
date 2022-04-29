package ru.homework.library.dao;

import ru.homework.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getById(long id);

    Author getByNameFamily(String name, String family);

    Author save(Author author);

    void deleteById(long id);

    void delete(Author author);

    List<Author> getAll();
}
