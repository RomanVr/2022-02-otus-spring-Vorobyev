package ru.homework.library.dao;

import ru.homework.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Optional<Book> getById(long id);

    Optional<Book> getRefById(long id);

    Book getByTitle(String title);

    Book save(Book book);

    void deleteById(long id);

    List<Book> getAll();

    List<Book> findBooksByAuthorId(long author_id);

    List<Book> findBooksByGenreId(long genre_id);
}
