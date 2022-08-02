package ru.homework.librarymvc.service;

import ru.homework.librarymvc.domain.BookCommentary;

import java.util.List;
import java.util.Optional;

public interface BookCommentService {
    Optional<BookCommentary> getById(long id);

    long insert(BookCommentary bc, long book_id);

    long update(BookCommentary bc);

    void deleteById(long id);

    List<BookCommentary> findCommentsByBookId(long book_id);
}
