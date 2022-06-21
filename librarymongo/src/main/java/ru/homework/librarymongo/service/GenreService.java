package ru.homework.librarymongo.service;

import ru.homework.librarymongo.domain.Genre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> getById(String id);

    Genre getByTitle(String title);

    String insert(Genre genre);

    String update(Genre genre);

    void deleteById(String id) throws SQLException;

    List<Genre> getAll();
}
