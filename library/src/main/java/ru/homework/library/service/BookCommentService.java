package ru.homework.library.service;

import ru.homework.library.domain.BookCommentary;

import java.util.List;
import java.util.Optional;

public interface BookCommentService {
    Optional<BookCommentary> getById(long id);

    long insert(BookCommentary bc, long book_id);

    long update(BookCommentary bc);

    void deleteById(long id);

    List<BookCommentary> findCommentsByBookId(long book_id);
}
