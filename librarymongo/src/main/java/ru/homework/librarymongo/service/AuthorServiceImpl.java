package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.repository.AuthorDao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> getAll() {
        return authorDao.findAll();
    }

    @Override
    @Transactional
    public String insert(Author newAuthor) {
        return authorDao.save(newAuthor).getId();
    }

    @Override
    @Transactional
    public String update(Author author) {
        return authorDao.save(author).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getById(String id) {
        return authorDao.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        authorDao.findById(id).ifPresent(authorDao::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByNameFamily(String name, String family) {
        List<Author> authors = authorDao.findByNameAndLastName(name, family);
        if (authors.size() > 0) {
            return authors.get(0);
        }
        throw new NoSuchElementException();
    }
}
