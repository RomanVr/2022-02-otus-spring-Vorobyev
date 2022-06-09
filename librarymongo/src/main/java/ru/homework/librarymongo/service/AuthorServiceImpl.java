package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.repository.AuthorDao;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public String insert(Author newAuthor) {
        return authorDao.save(newAuthor).getId();
    }

    @Override
    public String update(Author author) {
        if (author.getBookList() == null) {
            author.setBookList(new ArrayList<>());
        }
        return authorDao.save(author).getId();
    }

    @Override
    public Optional<Author> getById(String id) {
        return authorDao.findById(id);
    }

    @Override
    public void deleteById(String id) throws SQLException {
        var author = authorDao.findById(id).orElseThrow();
        if (author.getBookList().size() > 0) {
            throw new SQLException("Can't delete Author, present ref Book");
        }
        authorDao.delete(author);
    }

    @Override
    public Author getByNameFamily(String name, String family) {
        List<Author> authors = authorDao.findByNameAndLastName(name, family);
        if (authors.size() > 0) {
            return authors.get(0);
        }
        throw new NoSuchElementException();
    }
}
