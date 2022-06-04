package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.library.dao.BookCommentaryDao;
import ru.homework.library.dao.BookDao;
import ru.homework.library.domain.Book;
import ru.homework.library.domain.BookCommentary;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentaryDao commentaryDao;
    private final BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public Optional<BookCommentary> getById(long id) {
        return commentaryDao.findById(id);
    }

    @Override
    @Transactional
    public long insert(BookCommentary bc, long book_id) {
        Book book = bookDao.findById(book_id).orElseThrow();
        bc.setBook(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    @Transactional
    public long update(BookCommentary bc) {
        BookCommentary oldComm = commentaryDao.findById(bc.getId()).orElseThrow();
        Book book = bookDao.findById(oldComm.getBook().getId()).orElseThrow();
        bc.setBook(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentaryDao.findById(id).ifPresent(commentaryDao::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCommentary> findCommentsByBookId(long book_id) {
        var book = bookDao.getById(book_id);
        Hibernate.initialize(book.getBookCommentaries());
        return book.getBookCommentaries();
    }
}
