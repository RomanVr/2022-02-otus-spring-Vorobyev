package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.library.dao.GenreDao;
import ru.homework.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getByTitle(String title) {
        return genreDao.getByTitle(title);
    }

    @Override
    @Transactional
    public long insert(Genre genre) {
        return genreDao.save(genre).getId();
    }

    @Override
    @Transactional
    public long update(Genre genre) {
        return genreDao.save(genre).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        genreDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
