package ru.homework.librarymongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.homework.librarymongo.domain.Genre;

import java.util.List;

public interface GenreDao extends MongoRepository<Genre, Long> {
    List<Genre> findByGenreTitle(String title);
}
