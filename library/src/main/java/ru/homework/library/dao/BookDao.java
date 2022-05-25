package ru.homework.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.homework.library.domain.Book;

public interface BookDao extends JpaRepository<Book, Long> {
    Book findByBookTitle(String title);
}
