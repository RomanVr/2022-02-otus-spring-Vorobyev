package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.library.dao.AuthorDao;
import ru.homework.library.dao.BookDao;
import ru.homework.library.dao.GenreDao;
import ru.homework.library.domain.Author;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    @Transactional
    public long insert(Book book, long author_id, long genre_id) {
        Author author = authorDao.getRefById(author_id).get();
        Genre genre = genreDao.getRefById(genre_id).get();
        book.setAuthor(author);
        book.setGenre(genre);
        return bookDao.save(book).getId();
    }

    @Override
    @Transactional
    public long update(Book book) {
        return bookDao.save(book).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthorId(long author_id) {
        return bookDao.findBooksByAuthorId(author_id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByGenreId(long genre_id) {
        return bookDao.findBooksByGenreId(genre_id);
    }
}
