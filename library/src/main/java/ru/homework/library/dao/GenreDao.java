package ru.homework.library.dao;

import ru.homework.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(long id);

    Genre getByTitle(String title);

    long insert(Genre genre);

    long update(Genre genre);

    void deleteById(long id);

    List<Genre> getAll();
}
