package ru.homework.library.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.homework.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GenreDaoJpa implements GenreDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> getRefById(long id) {
        return Optional.ofNullable(em.getReference(Genre.class, id));
    }

    @Override
    public Genre getByTitle(String genreTitle) {
        TypedQuery<Genre> query = em.createQuery("select g " +
                "from Genre g " +
                "where g.genreTitle = :genreTitle", Genre.class);
        query.setParameter("genreTitle", genreTitle);
        return query.getSingleResult();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() < 1) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public void delete(Genre genre) {
        em.remove(genre);
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from Genre  g", Genre.class).getResultList();
    }
}
