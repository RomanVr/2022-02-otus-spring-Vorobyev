package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.homework.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Optional<Author> getRefById(long id) {
        return Optional.ofNullable(em.getReference(Author.class, id));
    }

    @Override
    public Author getByNameFamily(String name, String lastName) {
        TypedQuery<Author> query = em.createQuery("select a " +
                "from Author a " +
                "where a.name = :name and a.lastName = :lastName", Author.class);
        query.setParameter("name", name);
        query.setParameter("lastName", lastName);
        return query.getSingleResult();
    }

    @Override
    public Author save(Author author) {
        if (author.getId() < 1) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public void delete(Author author) {
        em.remove(author);
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }
}
