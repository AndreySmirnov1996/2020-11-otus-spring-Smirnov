package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.CommentEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CommentEntity comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Override
    public Optional<CommentEntity> findById(long id) {
        TypedQuery<CommentEntity> query = em.createQuery(
                "select c from CommentEntity c where c.id = :id", CommentEntity.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<CommentEntity> findAllByBookId(long bookId) {
        TypedQuery<CommentEntity> query = em.createQuery(
                "select c from CommentEntity c where c.book.id = :book_id", CommentEntity.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Override
    public List<CommentEntity> findAll() {
        TypedQuery<CommentEntity> query = em.createQuery("select c from CommentEntity c", CommentEntity.class);
        return query.getResultList();
    }

    @Override
    public void updateTextById(long id, String text) {
        Query query = em.createQuery("update CommentEntity c set c.text = :text where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("text", text);
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete from CommentEntity c where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAllByBookId(long bookId) {
        Query query = em.createQuery("delete from CommentEntity c where с.book.bookId = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }
}
