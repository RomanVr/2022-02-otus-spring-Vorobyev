package ru.homework.library.service;

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
        return commentaryDao.getById(id);
    }

    @Override
    @Transactional
    public long insert(BookCommentary bc, long book_id) {
        Book book = bookDao.getRefById(book_id).orElseThrow();
        bc.setBook(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    @Transactional
    public long update(BookCommentary bc) {
        BookCommentary oldComm = commentaryDao.getById(bc.getId()).orElseThrow();
        Book book = bookDao.getRefById(oldComm.getBook().getId()).orElseThrow();
        bc.setBook(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentaryDao.getRefById(id).ifPresent(commentaryDao::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCommentary> findCommentsByBookId(long book_id) {
        var book = bookDao.getRefById(book_id).orElseThrow();
        Hibernate.initialize(book.getBookCommentaries());
        return book.getBookCommentaries();
    }
}
