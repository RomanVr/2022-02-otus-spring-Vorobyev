package ru.homework.library.service;

import ru.homework.library.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    Genre getByTitle(String title);

    long insert(Genre genre);

    long update(Genre genre);

    void deleteById(long id);

    List<Genre> getAll();
}
