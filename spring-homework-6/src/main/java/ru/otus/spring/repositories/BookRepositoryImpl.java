package ru.otus.spring.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookEntity;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
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
        return Optional.ofNullable(em.find(BookEntity.class, id));
    }

    @Override
    public List<BookEntity> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("BookWithGenreAndAuthors");
        TypedQuery<BookEntity> query = em.createQuery("select s from BookEntity s", BookEntity.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();

        //return em.createQuery("select b from BookEntity b", BookEntity.class).getResultList();
    }

    @Override
    public void updateTitle(long bookId, String newTitle) {
        //TODO
        log.info("MOCK updateTitle");
    }

}
