package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.librarymongo.domain.Genre;
import ru.homework.librarymongo.repository.GenreDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Optional<Genre> getById(String id) {
        return genreDao.findById(id);
    }

    @Override
    public Genre getByTitle(String title) {
        List<Genre> genres = genreDao.findByGenreTitle(title);
        if (genres.size() > 0) {
            return genres.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override
    public String insert(Genre genre) {
        return genreDao.save(genre).getId();
    }

    @Override
    public String update(Genre genre) {
        if (genre.getBookList() == null) {
            genre.setBookList(new ArrayList<>());
        }
        return genreDao.save(genre).getId();
    }

    @Override
    public void deleteById(String id) throws SQLException {
        var genre = genreDao.findById(id).orElseThrow();
        if (genre.getBookList().size() > 0) {
            throw new SQLException("Can't delete Author, present ref Book");
        }
        genreDao.delete(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.findAll();
    }
}
