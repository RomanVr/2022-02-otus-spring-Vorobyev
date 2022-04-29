package ru.homework.library.dao;

import ru.homework.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Optional<Genre> getById(long id);

    Genre getByTitle(String title);

    Genre save(Genre genre);

    void deleteById(long id);

    List<Genre> getAll();
}
