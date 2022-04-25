package ru.homework.library.dao;

import ru.homework.library.domain.Book;

import java.util.List;

public interface BookDao {
    Book getById(long id);

    Book getByTitle(String title);

    long insert(Book book, long author_id, long genre_id);

    long update(Book book);

    void deleteById(long id);

    List<Book> getAll();

    List<Book> findBooksByAuthorId(long author_id);

    List<Book> findBooksByGenreId(long genre_id);
}
