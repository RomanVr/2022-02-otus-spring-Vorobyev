package ru.homework.library.dao;

import ru.homework.library.domain.BookCommentary;

import java.util.Optional;

public interface BookCommentaryDao {
    Optional<BookCommentary> getById(long id);

    Optional<BookCommentary> getRefById(long id);

    BookCommentary save(BookCommentary bookCommentary);

    void delete(BookCommentary commentary);
}
