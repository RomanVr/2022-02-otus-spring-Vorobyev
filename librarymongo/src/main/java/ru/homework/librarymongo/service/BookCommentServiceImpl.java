package ru.homework.librarymongo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Optional<BookCommentary> getById(String id) {
        return commentaryDao.findById(id);
    }

    @Override
    @Transactional
    public String insert(BookCommentary bc, String book_id) {
        Book book = bookDao.findById(book_id).orElseThrow();
        bc.setBook(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    @Transactional
    public String update(BookCommentary bc) {
        BookCommentary oldComm = commentaryDao.findById(bc.getId()).orElseThrow();
        Book book = bookDao.findById(oldComm.getBook().getId()).orElseThrow();
        bc.setBook(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        commentaryDao.findById(id).ifPresent(commentaryDao::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCommentary> findCommentsByBookId(String book_id) {
        var book = bookDao.findById(book_id).orElseThrow();
        return book.getBookCommentaries();
    }
}
