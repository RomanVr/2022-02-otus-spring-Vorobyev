package ru.homework.librarymongo.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.homework.librarymongo.domain.Book;

import java.util.List;

public interface BookDao extends MongoRepository<Book, String> {
    List<Book> findByBookTitle(String title);
}
