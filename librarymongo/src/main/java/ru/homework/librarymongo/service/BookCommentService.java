package ru.homework.librarymongo.service;

import ru.homework.librarymongo.domain.BookCommentary;

import java.util.List;
import java.util.Optional;

public interface BookCommentService {
    Optional<BookCommentary> getById(String id);

    String insert(BookCommentary bc, String book_id);

    String update(BookCommentary bc);

    void deleteById(String id);

    List<BookCommentary> findCommentsByBookId(String book_id);
}
