package ru.homework.librarymvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.librarymvc.repository.AuthorRepository;
import ru.homework.librarymvc.domain.Author;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public long insert(Author newAuthor) {
        return authorRepository.save(newAuthor).getId();
    }

    @Override
    @Transactional
    public long update(Author author) {
        return authorRepository.save(author).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> getById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        authorRepository.findById(id).ifPresent(authorRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByNameFamily(String name, String family) {
        return authorRepository.getByNameAndLastName(name, family);
    }
}
