package ru.homework.librarymongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.homework.librarymongo.domain.Genre;

import java.util.List;

public interface GenreDao extends MongoRepository<Genre, String> {
    List<Genre> findByGenreTitle(String title);
}
