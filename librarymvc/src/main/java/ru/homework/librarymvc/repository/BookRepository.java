package ru.homework.librarymvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.librarymvc.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookTitle(String title);
}
