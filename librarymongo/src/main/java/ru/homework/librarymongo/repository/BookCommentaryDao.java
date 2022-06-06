package ru.homework.librarymongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.homework.librarymongo.domain.BookCommentary;

public interface BookCommentaryDao extends MongoRepository<BookCommentary, String> {
}
