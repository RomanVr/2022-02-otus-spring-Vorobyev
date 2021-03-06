package ru.homework.library.service;

import ru.homework.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> getById(long id);

    Genre getByTitle(String title);

    long insert(Genre genre);

    long update(Genre genre);

    void deleteById(long id);

    List<Genre> getAll();
}
