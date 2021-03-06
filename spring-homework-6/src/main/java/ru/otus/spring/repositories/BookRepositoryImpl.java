package ru.otus.spring.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(long bookId) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
    }

    @Override
    public void save(Book book) {
        authorRepository.saveAll(book.getAuthors());
        genreRepository.save(book.getGenre());
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public void updateTitleById(long bookId, String newTitle) {
        Query query = em.createQuery("update Book b set b.title = :title where b.id = :id");
        query.setParameter("title", newTitle);
        query.setParameter("id", bookId);
        query.executeUpdate();
    }

}
