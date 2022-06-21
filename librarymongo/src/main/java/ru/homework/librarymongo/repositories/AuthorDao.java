package ru.homework.librarymongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.homework.librarymongo.domain.Author;

import java.util.List;

public interface AuthorDao extends MongoRepository<Author, String> {
    List<Author> findByNameAndLastName(String name, String lastName);
}
