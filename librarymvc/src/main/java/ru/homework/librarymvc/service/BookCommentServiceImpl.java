package ru.homework.librarymvc.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.librarymvc.repository.BookCommentaryRepository;
import ru.homework.librarymvc.repository.BookRepository;
import ru.homework.librarymvc.domain.Book;
import ru.homework.librarymvc.domain.BookCommentary;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {
    private final BookCommentaryRepository commentaryRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<BookCommentary> getById(long id) {
        return commentaryRepository.findById(id);
    }

    @Override
    @Transactional
    public long insert(BookCommentary bc, long book_id) {
        Book book = bookRepository.findById(book_id).orElseThrow();
        bc.setBook(book);
        return commentaryRepository.save(bc).getId();
    }

    @Override
    @Transactional
    public long update(BookCommentary bc) {
        BookCommentary oldComm = commentaryRepository.findById(bc.getId()).orElseThrow();
        Book book = bookRepository.findById(oldComm.getBook().getId()).orElseThrow();
        bc.setBook(book);
        return commentaryRepository.save(bc).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentaryRepository.findById(id).ifPresent(commentaryRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCommentary> findCommentsByBookId(long book_id) {
        var book = bookRepository.getById(book_id);
        Hibernate.initialize(book.getBookCommentaries());
        return book.getBookCommentaries();
    }
}
