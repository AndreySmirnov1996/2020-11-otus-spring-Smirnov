package ru.otus.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

    //void save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateTitleById(long bookId, String newTitle);

    void delete(long bookId);
}
