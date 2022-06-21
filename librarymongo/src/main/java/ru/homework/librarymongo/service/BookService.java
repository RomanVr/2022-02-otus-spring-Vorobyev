package ru.homework.librarymongo.service;

import ru.homework.librarymongo.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getById(String id);

    Book getByTitle(String title);

    String insert(Book book, String author_id, String genre_id);

    String update(Book book);

    void deleteById(String id);

    List<Book> getAll();

    List<Book> findBooksByAuthorId(String author_id);

    List<Book> findBooksByGenreId(String genre_id);
}
