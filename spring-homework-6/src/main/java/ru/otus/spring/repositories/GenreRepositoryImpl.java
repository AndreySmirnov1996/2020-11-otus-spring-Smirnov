package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.GenreEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(GenreEntity genre) {
        em.persist(genre);
    }

    @Override
    public List<GenreEntity> findAll() {
        return em.createQuery("select e from GenreEntity e", GenreEntity.class).getResultList();
    }
}
