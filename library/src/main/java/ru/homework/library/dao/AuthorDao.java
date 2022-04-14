package ru.homework.library.dao;

import ru.homework.library.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(long id);

    Author getByNameFamily(String name, String family);

    long insert(Author author);

    long update(Author author);

    void deleteById(long id);

    List<Author> getAll();
}
