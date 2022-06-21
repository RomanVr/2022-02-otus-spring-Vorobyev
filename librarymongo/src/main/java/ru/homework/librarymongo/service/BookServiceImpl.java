package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.librarymongo.domain.Author;
import ru.homework.librarymongo.domain.Book;
import ru.homework.librarymongo.domain.Genre;
import ru.homework.librarymongo.repositories.AuthorDao;
import ru.homework.librarymongo.repositories.BookDao;
import ru.homework.librarymongo.repositories.GenreDao;

import java.util.ArrayList;
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
    public Optional<Book> getById(String id) {
        return bookDao.findById(id);
    }

    @Override
    public Book getByTitle(String title) {
        List<Book> books = bookDao.findByBookTitle(title);
        if (books.size() > 0) {
            return books.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override
    public String insert(Book book, String author_id, String genre_id) {
        Author author = authorDao.findById(author_id).orElseThrow();
        Genre genre = genreDao.findById(genre_id).orElseThrow();
        book.setAuthor(author);
        book.setGenre(genre);
        var idBook = bookDao.save(book).getId();
        author.getBookList().add(book);
        genre.getBookList().add(book);
        authorDao.save(author);
        genreDao.save(genre);
        return idBook;
    }

    @Override
    public String update(Book book) {
        if (book.getBookCommentaries() == null) {
            book.setBookCommentaries(new ArrayList<>());
        }
        return bookDao.save(book).getId();
    }

    @Override
    public void deleteById(String id) {
        var book = bookDao.findById(id).orElseThrow();
        var author = book.getAuthor();
        var genre = book.getGenre();
        author.getBookList().remove(book);
        authorDao.save(author);
        genre.getBookList().remove(book);
        genreDao.save(genre);
        bookDao.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findBooksByAuthorId(String author_id) {
        var author = authorDao.findById(author_id).orElseThrow();
        return author.getBookList();
    }

    @Override
    public List<Book> findBooksByGenreId(String genre_id) {
        var genre = genreDao.findById(genre_id).orElseThrow();
        return genre.getBookList();
    }
}
