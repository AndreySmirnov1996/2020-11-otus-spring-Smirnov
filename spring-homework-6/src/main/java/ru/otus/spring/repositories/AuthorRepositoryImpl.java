package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.AuthorEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(AuthorEntity author) {
        if (author.getId() <= 0) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }

    @Override
    public void saveAll(List<AuthorEntity> authorList) {
        authorList.forEach(auth -> {
            if (auth.getName() != null && auth.getSurname() != null) {
                save(auth);
            }
        });
    }

    @Override
    public Optional<AuthorEntity> findById(long id) {

        TypedQuery<AuthorEntity> query = em.createQuery(
                "select e from AuthorEntity e where e.id = :id", AuthorEntity.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
