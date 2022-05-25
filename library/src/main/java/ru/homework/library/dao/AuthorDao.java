package ru.homework.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.library.domain.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
    Author getByNameAndLastName(String name, String family);
}
