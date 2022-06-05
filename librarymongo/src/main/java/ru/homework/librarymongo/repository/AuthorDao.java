package ru.homework.librarymongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.homework.librarymongo.domain.Author;

import java.util.List;

public interface AuthorDao extends MongoRepository<Author, Long> {
    List<Author> findByNameAndLastName(String name, String lastName);
}
