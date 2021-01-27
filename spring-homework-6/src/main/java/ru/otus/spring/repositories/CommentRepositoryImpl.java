package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.CommentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CommentEntity book) {

    }

    @Override
    public Optional<CommentEntity> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<CommentEntity> findAllByBookId(long bookId) {
        return null;
    }

    @Override
    public List<CommentEntity> findAll() {
        return null;
    }

    @Override
    public void updateText(long bookId, String text) {

    }

    @Override
    public void delete(long id) {
//        Query query = em.createQuery("delete from CommentEntity c where c.book.bookId = :bookId");
//        query.setParameter("bookId", bookId);
//        query.executeUpdate();
    }
}
