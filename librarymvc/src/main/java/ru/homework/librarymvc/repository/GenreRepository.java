package ru.homework.librarymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.librarymvc.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreTitle(String title);
}
