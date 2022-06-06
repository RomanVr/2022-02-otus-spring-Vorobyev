package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.domain.Book;
import ru.homework.librarymongo.domain.Genre;
import ru.homework.librarymongo.repository.AuthorDao;
import ru.homework.librarymongo.repository.BookDao;
import ru.homework.librarymongo.repository.GenreDao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getById(String id) {
        return bookDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByTitle(String title) {
        List<Book> books = bookDao.findByBookTitle(title);
        if (books.size() > 0) {
            return books.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override
    @Transactional
    public String insert(Book book, String author_id, String genre_id) {
        Author author = authorDao.findById(author_id).orElseThrow();
        Genre genre = genreDao.findById(genre_id).orElseThrow();
        book.setAuthor(author);
        book.setGenre(genre);
        return bookDao.save(book).getId();
    }

    @Override
    @Transactional
    public String update(Book book) {
        return bookDao.save(book).getId();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        bookDao.findById(id).ifPresent(bookDao::delete);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthorId(String author_id) {
        var author = authorDao.findById(author_id).orElseThrow();
        return author.getBookList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByGenreId(String genre_id) {
        var genre = genreDao.findById(genre_id).orElseThrow();
        return genre.getBookList();
    }
}
