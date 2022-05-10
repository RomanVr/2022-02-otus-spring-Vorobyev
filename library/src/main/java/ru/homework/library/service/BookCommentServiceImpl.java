package ru.homework.library.service;

import lombok.RequiredArgsConstructor;
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
    public Optional<BookCommentary> getById(long id) {
        return commentaryDao.getById(id);
    }

    @Override
    public long insert(BookCommentary bc, long book_id) {
        Book book = bookDao.getById(book_id).get();
        bc.setBook(book);
        return commentaryDao.save(bc).getId();
    }

    @Override
    public long update(BookCommentary bc) {
        return commentaryDao.save(bc).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentaryDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCommentary> findCommentsByBookId(long book_id) {
        return commentaryDao.findCommentsByBookId(book_id);
    }
}
