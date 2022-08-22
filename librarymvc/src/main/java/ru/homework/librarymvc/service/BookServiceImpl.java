package ru.homework.librarymvc.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.librarymvc.domain.Author;
import ru.homework.librarymvc.domain.Book;
import ru.homework.librarymvc.domain.Genre;
import ru.homework.librarymvc.repository.AuthorRepository;
import ru.homework.librarymvc.repository.BookRepository;
import ru.homework.librarymvc.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByTitle(String title) {
        return bookRepository.findByBookTitle(title);
    }

    @Override
    @Transactional
    public long insert(Book book, long author_id, long genre_id) {
        Author author = authorRepository.findById(author_id).orElseThrow();
        Genre genre = genreRepository.findById(genre_id).orElseThrow();
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book).getId();
    }

    @Override
    @Transactional
    public long update(Book newBook, long author_id, long genre_id) {
        Author author = authorRepository.findById(author_id).orElseThrow();
        Genre genre = genreRepository.findById(genre_id).orElseThrow();
        Book book = bookRepository.getById(newBook.getId());
        book.setBookTitle(newBook.getBookTitle());
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book).getId();

    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.findById(id).ifPresent(bookRepository::delete);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthorId(long author_id) {
        var author = authorRepository.findById(author_id).orElseThrow();
        Hibernate.initialize(author.getBookList());
        return author.getBookList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByGenreId(long genre_id) {
        var genre = genreRepository.findById(genre_id).orElseThrow();
        Hibernate.initialize(genre.getBookList());
        return genre.getBookList();
    }
}
