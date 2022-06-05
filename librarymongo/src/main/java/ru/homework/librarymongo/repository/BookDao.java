package ru.homework.librarymongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.homework.librarymongo.domain.Book;

import java.util.List;

public interface BookDao extends MongoRepository<Book, Long> {
    List<Book> findByBookTitle(String title);
}
