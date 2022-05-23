package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.library.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookDaoJpa implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public Optional<Book> getRefById(long id) {
        return Optional.ofNullable(em.getReference(Book.class, id));
    }

    @Override
    public Book getByTitle(String bookTitle) {
        TypedQuery<Book> query = em.createQuery("select b " +
                "from Book b " +
                "where b.bookTitle = :bookTitle", Book.class);
        query.setParameter("bookTitle", bookTitle);
        return query.getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() < 1) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public void delete(Book book) {
        em.remove(book);
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b ", Book.class).getResultList();
    }
}
