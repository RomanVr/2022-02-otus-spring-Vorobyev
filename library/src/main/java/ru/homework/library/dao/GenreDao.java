package ru.homework.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.library.domain.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
    Genre findByGenreTitle(String title);
}
