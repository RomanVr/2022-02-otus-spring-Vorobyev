package ru.homework.library.dao;

import ru.homework.library.domain.BookCommentary;

import java.util.List;
import java.util.Optional;

public interface BookCommentaryDao {
    Optional<BookCommentary> getById(long id);

    BookCommentary save(BookCommentary bookCommentary);

    void deleteById(long id);

    List<BookCommentary> findCommentsByBookId(long book_id);
}
