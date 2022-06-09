package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.homework.librarymongo.domain.Book;
import ru.homework.librarymongo.domain.BookCommentary;
import ru.homework.librarymongo.repository.BookCommentaryDao;
import ru.homework.librarymongo.repository.BookDao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentaryDao commentaryDao;
    private final BookDao bookDao;

    @Override
    public Optional<BookCommentary> getById(String id) {
        return commentaryDao.findById(id);
    }

    @Override
    public String insert(BookCommentary bc, String book_id) {
        Book book = bookDao.findById(book_id).orElseThrow();
        bc.setBook(book);
        var idBc = commentaryDao.save(bc).getId();
        book.getBookCommentaries().add(bc);
        bookDao.save(book);
        return idBc;
    }

    @Override
    public String update(BookCommentary bc) {
        BookCommentary oldComm = commentaryDao.findById(bc.getId()).orElseThrow();
        Book book = bookDao.findById(oldComm.getBook().getId()).orElseThrow();
        bc.setBook(book);
        bookDao.save(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    public void deleteById(String id) {
        var bc = commentaryDao.findById(id).orElseThrow();
        var book = bookDao.findById(bc.getBook().getId()).orElseThrow();
        book.getBookCommentaries().remove(bc);
        bookDao.save(book);
        commentaryDao.delete(bc);
    }

    @Override
    public List<BookCommentary> findCommentsByBookId(String book_id) {
        var book = bookDao.findById(book_id).orElseThrow();
        return book.getBookCommentaries();
    }
}
