package ru.homework.library.service;

import ru.homework.library.domain.Book;
import ru.homework.library.domain.Genre;

import java.util.List;

public interface BookService {
    Book getById(long id);

    Book getByTitle(String title);

    long insert(Book book);

    long update(Book book);

    void deleteById(long id);

    List<Book> getAll();
}
