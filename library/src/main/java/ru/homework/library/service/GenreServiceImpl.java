package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.library.dao.GenreDao;
import ru.homework.library.domain.Genre;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre getByTitle(String title) {
        return genreDao.getByTitle(title);
    }

    @Override
    public long insert(Genre genre) {
        return genreDao.insert(genre);
    }

    @Override
    public long update(Genre genre) {
        return genreDao.update(genre);
    }

    @Override
    public void deleteById(long id) {
        genreDao.deleteById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
