package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.library.dao.AuthorDao;
import ru.homework.library.domain.Author;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    @Transactional
    public long insert(Author newAuthor) {
        return authorDao.save(newAuthor).getId();
    }

    @Override
    @Transactional
    public long update(Author author) {
        return authorDao.save(author).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        authorDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByNameFamily(String name, String family) {
        return authorDao.getByNameFamily(name, family);
    }
}
