package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.librarymongo.domain.Book;
import ru.homework.librarymongo.domain.BookCommentary;
import ru.homework.librarymongo.repositories.BookDao;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookDao bookDao;

    @Override
    public Optional<BookCommentary> getById(String bc_id, String book_id) {
        Book book = bookDao.findById(book_id).orElseThrow();
        var bc = book.getBookCommentaries().stream().filter(bookCommentary -> bookCommentary.getId().equals(bc_id)).findFirst();
        return bc;
    }

    @Override
    public String insert(BookCommentary bc, String book_id) {
        Book book = bookDao.findById(book_id).orElseThrow();
        bc.setId(UUID.randomUUID().toString());
        book.getBookCommentaries().add(bc);
        bookDao.save(book);
        return bc.getId();
    }

    @Override
    public String update(BookCommentary newBc, String book_id) {
        Book book = bookDao.findById(book_id).orElseThrow();
        BookCommentary oldComm = book
                .getBookCommentaries()
                .stream()
                .filter(elem -> elem.equals(newBc))
                .findFirst()
                .orElseThrow();
        oldComm.setCommentary(newBc.getCommentary());
        bookDao.save(book);
        return newBc.getId();
    }

    @Override
    public void deleteById(String id, String book_id) {
        var book = bookDao.findById(book_id).orElseThrow();
        BookCommentary delComm = book
                .getBookCommentaries()
                .stream()
                .filter(elem -> elem.getId().equals(id))
                .findFirst()
                .orElseThrow();
        book.getBookCommentaries().remove(delComm);
        bookDao.save(book);
    }

    @Override
    public List<BookCommentary> findCommentsByBookId(String book_id) {
        var book = bookDao.findById(book_id).orElseThrow();
        return book.getBookCommentaries();
    }
}
