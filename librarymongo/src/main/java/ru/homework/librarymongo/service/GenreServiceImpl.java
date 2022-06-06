package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.librarymongo.domain.Genre;
import ru.homework.librarymongo.repository.GenreDao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getById(String id) {
        return genreDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getByTitle(String title) {
        List<Genre> genres = genreDao.findByGenreTitle(title);
        if (genres.size() > 0) {
            return genres.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override
    @Transactional
    public String insert(Genre genre) {
        return genreDao.save(genre).getId();
    }

    @Override
    @Transactional
    public String update(Genre genre) {
        return genreDao.save(genre).getId();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        genreDao.findById(id).ifPresent(genreDao::delete);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.findAll();
    }
}
