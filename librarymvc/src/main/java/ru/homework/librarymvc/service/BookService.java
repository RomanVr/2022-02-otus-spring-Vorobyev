package ru.homework.librarymvc.service;

import ru.homework.librarymvc.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getById(long id);

    Book getByTitle(String title);

    long insert(Book book, long author_id, long genre_id);

    long update(Book book, long author_id, long genre_id);

    void deleteById(long id);

    List<Book> getAll();

    List<Book> findBooksByAuthorId(long author_id);

    List<Book> findBooksByGenreId(long genre_id);
}
