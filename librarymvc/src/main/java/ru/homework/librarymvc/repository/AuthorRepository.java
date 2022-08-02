package ru.homework.librarymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.librarymvc.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getByNameAndLastName(String name, String family);
}
