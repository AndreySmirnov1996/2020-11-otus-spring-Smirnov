package ru.otus.spring.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(long bookId) {
        //TODO
        log.info("MOCK delete");
    }


    @Override
    public void save(BookEntity book) {
        em.persist(book);
    }

    @Override
    public Optional<BookEntity> findById(long id) {
        TypedQuery<BookEntity> query = em.createQuery(
                "select e from BookEntity e where e.id = :id"
                , BookEntity.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BookEntity> findAll() {
        TypedQuery<BookEntity> query = em.createQuery("select s from BookEntity s", BookEntity.class);
        return query.getResultList();
    }

    @Override
    public void updateTitle(long bookId, String newTitle) {
        //TODO
        log.info("MOCK updateTitle");
    }

}
