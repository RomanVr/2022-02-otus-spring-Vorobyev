package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.library.dao.AuthorDao;
import ru.homework.library.domain.Author;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public long insert(Author newAuthor) {
        return authorDao.insert(newAuthor);
    }

    @Override
    public long update(Author author) {
        return authorDao.update(author);
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public void deleteById(long id) {
        authorDao.deleteById(id);
    }
}
