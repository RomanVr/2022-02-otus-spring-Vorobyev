package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
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
        return bookDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByTitle(String title) {
        return bookDao.findByBookTitle(title);
    }

    @Override
    @Transactional
    public long insert(Book book, long author_id, long genre_id) {
        Author author = authorDao.getById(author_id);
        Genre genre = genreDao.findById(genre_id).orElseThrow();
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
        bookDao.findById(id).ifPresent(bookDao::delete);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthorId(long author_id) {
        var author = authorDao.getById(author_id);
        Hibernate.initialize(author.getBookList());
        return author.getBookList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByGenreId(long genre_id) {
        var genre = genreDao.findById(genre_id).orElseThrow();
        Hibernate.initialize(genre.getBookList());
        return genre.getBookList();
    }
}
