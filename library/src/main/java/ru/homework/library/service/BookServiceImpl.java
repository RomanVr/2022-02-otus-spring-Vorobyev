package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.library.dao.BookDao;
import ru.homework.library.domain.Book;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public long insert(Book book, long author_id, long genre_id) {
        return bookDao.insert(book, author_id, genre_id);
    }

    @Override
    public long update(Book book) {
        return bookDao.update(book);
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> findBooksByAuthorId(long author_id) {
        return bookDao.findBooksByAuthorId(author_id);
    }

    @Override
    public List<Book> findBooksByGenreId(long genre_id) {
        return bookDao.findBooksByGenreId(genre_id);
    }
}
