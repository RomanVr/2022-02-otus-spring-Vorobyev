package ru.homework.librarymvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.librarymvc.repository.GenreRepository;
import ru.homework.librarymvc.domain.Genre;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getById(long id) {
        return genreRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getByTitle(String title) {
        return genreRepository.findByGenreTitle(title);
    }

    @Override
    @Transactional
    public long insert(Genre genre) {
        return genreRepository.save(genre).getId();
    }

    @Override
    @Transactional
    public long update(Genre genre) {
        return genreRepository.save(genre).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        genreRepository.findById(id).ifPresent(genreRepository::delete);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
