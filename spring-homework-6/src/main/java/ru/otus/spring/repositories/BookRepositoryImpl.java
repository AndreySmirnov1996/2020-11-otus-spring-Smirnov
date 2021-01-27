package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(long bookId) {
        Query query = em.createQuery("delete from BookEntity b where b.id = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
    }


    @Override
    public void save(BookEntity book) {
        authorRepository.saveAll(book.getAuthors());
        genreRepository.save(book.getGenre());
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public Optional<BookEntity> findById(long id) {
        TypedQuery<BookEntity> query = em.createQuery(
                "select e from BookEntity e where e.id = :id", BookEntity.class);
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
    public void updateTitleById(long bookId, String newTitle) {
        Query query = em.createQuery("update BookEntity b " +
                "set b.title = :title " +
                "where b.id = :id");
        query.setParameter("title", newTitle);
        query.setParameter("id", bookId);
        query.executeUpdate();
    }

}
