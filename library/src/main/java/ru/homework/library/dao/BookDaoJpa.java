package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
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
    public void deleteById(long id) {
        var query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b ", Book.class).getResultList();
    }

    @Override
    public List<Book> findBooksByAuthorId(long author_id) {
        var query = em.createQuery("select b from Book b " +
                "WHERE b.author.id = :author_id", Book.class);
        query.setParameter("author_id", author_id);
        return query.getResultList();
    }

    @Override
    public List<Book> findBooksByGenreId(long genre_id) {
        var query = em.createQuery("select b from Book b " +
                "where b.genre.id = :genre_id", Book.class);
        query.setParameter("genre_id", genre_id);
        return query.getResultList();
    }
}
