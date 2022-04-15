package ru.homework.library.dao;

import ru.homework.library.domain.Book;

import java.util.List;

public interface BookDao {
    Book getById(long id);

    Book getByTitle(String title);

    long insert(Book book);

    long update(Book book);

    void deleteById(long id);

    List<Book> getAll();
}
