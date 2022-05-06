package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.BookCommentary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookCommentaryDaoJpa implements BookCommentaryDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<BookCommentary> getById(long id) {
        return Optional.ofNullable(em.find(BookCommentary.class, id));
    }

    @Override
    public BookCommentary save(BookCommentary bookCommentary) {
        if (bookCommentary.getId() < 1) {
            em.persist(bookCommentary);
            return bookCommentary;
        }
        return em.merge(bookCommentary);
    }

    @Override
    public void deleteById(long id) {
        var query = em.createQuery("delete from BookCommentary bc where bc.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<BookCommentary> findCommentsByBookId(long book_id) {
        var query = em.createQuery("select bc from BookCommentary bc where bc.id = :book_id", BookCommentary.class);
        return query.getResultList();
    }
}
