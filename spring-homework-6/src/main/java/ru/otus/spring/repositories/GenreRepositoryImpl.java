package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Genre genre) {
        if (genre.getName() != null) {
            if (genre.getId() <= 0) {
                em.persist(genre);
            } else {
                em.merge(genre);
            }
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return em.createQuery("select e from Genre e", Genre.class).getResultList();
    }
}
