package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.id = :id", Comment.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Comment> findAllByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.book.id = :book_id", Comment.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public void updateTextById(long id, String text) {
        Query query = em.createQuery("update Comment c set c.text = :text where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("text", text);
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete from Comment c where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAllByBookId(long bookId) {
        Query query = em.createQuery("delete from Comment c where с.book.bookId = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }
}
